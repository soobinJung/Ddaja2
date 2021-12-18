package com.bng.ddaja.users.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.security.sasl.AuthenticationException;

import com.bng.ddaja.common.api.social.SocialResponse;
import com.bng.ddaja.common.config.exception.exception.MemberNotFoundException;
import com.bng.ddaja.common.config.exception.exception.NotAcceptableSocialLoginException;
import com.bng.ddaja.common.config.exception.exception.NotAcceptableSocialResponseException;
import com.bng.ddaja.common.domain.Token;
import com.bng.ddaja.common.domain.User;
import com.bng.ddaja.common.dto.CommonUserDetails;
import com.bng.ddaja.common.dto.SocialAccessToken;
import com.bng.ddaja.common.enums.HttpMethods;
import com.bng.ddaja.common.util.Constants;
import com.bng.ddaja.common.util.OKHttp;
import com.bng.ddaja.tokens.repository.TokenRepository;
import com.bng.ddaja.tokens.service.TokenService;
import com.bng.ddaja.users.dto.UserDTO;
import com.bng.ddaja.users.repository.UserRepository;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Response;

@Slf4j
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private TokenService tokenService;

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(v -> new UserDTO(v)).collect(Collectors.toList());
    }

    public UserDTO getUserById(long id) {
        return new UserDTO(userRepository.findById(id));
    }

    public UserDTO getUserBySocialToken(SocialAccessToken socialAccessToken) throws IOException, MemberNotFoundException, NotAcceptableSocialLoginException {
        SocialResponse socialResponse;
        if(socialAccessToken.getTokenType() == null) throw new NotAcceptableSocialLoginException("Social Login Type is Not Included");
        switch(socialAccessToken.getTokenType()) {
            case KAKAO:
                socialResponse = requestKakaoUserInfo(socialAccessToken);
                break;
            default:
                throw new NotAcceptableSocialLoginException();
        }
        Token token = tokenRepository.findByClientID(socialResponse.getId());
        if(token == null) return createUserBySocialResponse(socialResponse);
        if(token.getUser() == null) throw new MemberNotFoundException("Token Info Valid But Member Not Founded");
        UserDTO userDTO = new UserDTO(token.getUser());
        userDTO.setJwt(tokenService.getCommonJWTByUserDTO(userDTO).getJwt());
        return userDTO;
    }

    public UserDTO patchUserByUserDTO(UserDTO userDTO) {
        Optional<User> originUser = userRepository.findOptionalUserById(userDTO.getId());
        if(!originUser.isPresent()) throw new MemberNotFoundException("해당 ID의 사용자가 존재하지 않습니다.");
        return new UserDTO(userRepository.save(userDTO.toEntity()));
    }

    private UserDTO createUserBySocialResponse(SocialResponse socialResponse) throws AuthenticationException {
        User user = userRepository.save(User.builder().build());
        tokenRepository.save(Token.builder().clientID(socialResponse.getId()).user(user).build());
        UserDTO userDTO = new UserDTO(user, true);
        userDTO.setJwt(tokenService.getCommonJWTByUserDTO(userDTO).getJwt());
        return userDTO;
    }

    private SocialResponse requestKakaoUserInfo(SocialAccessToken socialAccessToken) throws IOException {
        String authorizationValue = Constants.BEARER + " " + socialAccessToken.getAccessToken();
        Response response = OKHttp.okHttpRequest("https://kapi.kakao.com/v2/user/me", new Headers.Builder().add(Constants.AUTHORIZATION, authorizationValue).build(), null, HttpMethods.GET);
        if(!response.isSuccessful()) {
            throw new NotAcceptableSocialResponseException();
        }
        SocialResponse result = new Moshi.Builder()
                                            .add(Date.class, new Rfc3339DateJsonAdapter())
                                            .build()
                                            .adapter(SocialResponse.class)
                                            .fromJson(response.body()
                                            .source());
        response.body().close();
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException { 
        return new CommonUserDetails();
    }
}
