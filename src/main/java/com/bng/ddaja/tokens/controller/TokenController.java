package com.bng.ddaja.tokens.controller;

import javax.security.sasl.AuthenticationException;

import com.bng.ddaja.common.dto.CommonResponse;
import com.bng.ddaja.tokens.service.TokenService;
import com.bng.ddaja.users.dto.UserDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RequestMapping("tokens")
@RestController
public class TokenController {
    private TokenService tokensService;


    @PostMapping("")
    public ResponseEntity<CommonResponse> createToken(@RequestBody UserDTO userDTO) throws AuthenticationException {
        return ResponseEntity.ok().body(new CommonResponse(tokensService.getCommonJWTByUserDTO(userDTO)));
    }

}
