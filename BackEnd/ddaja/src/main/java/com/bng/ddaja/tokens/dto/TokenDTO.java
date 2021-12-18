package com.bng.ddaja.tokens.dto;

import java.util.Date;

import com.bng.ddaja.common.domain.Token;
import com.bng.ddaja.common.domain.Token.TokenBuilder;
import com.bng.ddaja.common.dto.CommonDTO;
import com.bng.ddaja.users.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TokenDTO extends CommonDTO {
    
    private String access;
    
    private String refresh;

    private Date expire;

    private String issuance;

    private String clientID;

    private UserDTO user;

    public TokenDTO(Token vo) {
        if(vo == null) return;
        id = vo.getId();
        access = vo.getAccess();
        refresh = vo.getRefresh();
        expire = vo.getExpire();
        issuance = vo.getIssuance();
        clientID = vo.getClientID();
        user = vo.getUser() == null ? null : new UserDTO(vo.getUser());
    }

    public Token toEntity() {
        TokenBuilder builder = Token.builder()
                    .id(id)
                    .access(access)
                    .refresh(refresh)
                    .expire(expire)
                    .issuance(issuance)
                    .clientID(clientID);
        if(user != null) builder.user(user.toEntity());
        return builder.build();
    }
}
