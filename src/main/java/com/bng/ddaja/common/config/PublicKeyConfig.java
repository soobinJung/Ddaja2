package com.bng.ddaja.common.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "eval.publickey")
public class PublicKeyConfig {
    private String publicKey;

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(publicKey.getBytes(StandardCharsets.UTF_8));
    }
}
