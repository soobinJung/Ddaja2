package com.bng.ddaja.common.config;

import java.util.List;

import com.bng.ddaja.common.config.interceptor.JWTInterceptor;
import com.bng.ddaja.common.config.resolver.JWTArgumentResolver;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class CustomMvcConfigurer implements WebMvcConfigurer {

    private JWTInterceptor jwtInterceptor;
    private JWTArgumentResolver jwtArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jwtArgumentResolver);
    }
}
