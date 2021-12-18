package com.bng.ddaja.tokens.repository;

import com.bng.ddaja.common.domain.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TokenRepository extends JpaRepository<Token, Long>, JpaSpecificationExecutor<Token> {
    public Token findByClientID(String clientID);
}