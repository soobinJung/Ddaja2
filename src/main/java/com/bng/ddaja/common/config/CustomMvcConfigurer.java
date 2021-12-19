package com.bng.ddaja.common.config;

import java.util.List;

import com.bng.ddaja.common.config.interceptor.JWTInterceptor;
import com.bng.ddaja.common.config.resolver.JWTArgumentResolver;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebMvc
@AllArgsConstructor
public class CustomMvcConfigurer implements WebMvcConfigurer {

    private JWTInterceptor jwtInterceptor;
    private JWTArgumentResolver jwtArgumentResolver;

    private static final String VUE_RESOURCE = "classpath:/static/view/";
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(jwtArgumentResolver);
    }
    
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/view/**").addResourceLocations(VUE_RESOURCE);
	}
}
