package com.bng.ddaja.common.dto;

import com.bng.ddaja.common.enums.Roles;
import com.bng.ddaja.common.util.Constants;
import com.bng.ddaja.users.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@JsonIgnoreProperties({ "id", "userID", "userName", "role", "claims" })
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonJWT extends CommonDTO {
    private String jwt;
    private long id;
    private String userID;
    private String userName;
    private Roles role;
    private Claims claims;
    private boolean isValidated;

    public CommonJWT(UserDTO userDTO) {
        id = userDTO.getId();
        userID = userDTO.getUserId();
        userName = userDTO.getNickName();
        role = Roles.USER;
        isValidated = true;
    }
    public CommonJWT(long id,  String userID, String userName, Roles role) {
        this.id = id;
        this.userID = userID;
        this.userName = userName;
        this.role = role;
    }

    public CommonJWT(String jwt, Claims claims) {
        this.jwt = jwt;
        if(claims == null) return;
        this.claims = claims;
        id = Integer.toUnsignedLong((int) claims.get(Constants.CLAIMS_ID));
        userID = (String) claims.get(Constants.CLAIMS_USER_ID);
        userName = (String) claims.get(Constants.CLAIMS_USER_NAME);
        role = Roles.valueOf((String) claims.get(Constants.CLAIMS_ROLE));
        this.isValidated = true;
    }

    public CommonJWT(boolean isvalidated) {
        this.isValidated = isvalidated;
    }

    public CommonJWT(String jwt) {
        this.jwt = jwt;
    }
}
