package com.bng.ddaja.common.config.resolver;

import com.bng.ddaja.common.dto.CommonJWT;
import com.bng.ddaja.common.util.Constants;
import com.bng.ddaja.tokens.service.TokenService;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTArgumentResolver implements HandlerMethodArgumentResolver {
    
    private TokenService tokenService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return CommonJWT.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return tokenService.getCommonJWTByJWT(webRequest.getHeader(Constants.AUTHORIZATION));
    }
}
