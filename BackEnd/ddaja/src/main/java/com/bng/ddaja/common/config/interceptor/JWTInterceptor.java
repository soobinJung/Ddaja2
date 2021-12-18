package com.bng.ddaja.common.config.interceptor;

import java.util.Arrays;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bng.ddaja.common.enums.AuthRequiredURLs;
import com.bng.ddaja.tokens.service.TokenService;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTInterceptor implements HandlerInterceptor {
    
    private TokenService tokenService;

    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception  {
        if(request.getMethod().equals("OPTIONS")) return true;
        boolean isAuthRequiredURL = Arrays.stream(AuthRequiredURLs.values()).anyMatch(e-> request.getRequestURI().contains(e.getName()));
        if(!isAuthRequiredURL) return true;
        String jwt = request.getHeader("Authorization");
        if(jwt == null) throw new AuthenticationException("JWT is Required");
        return tokenService.isValidatedJWT(jwt);
    }
}