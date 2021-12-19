package com.bng.ddaja.common.hateoas.users;

import com.bng.ddaja.common.dto.Link;
import com.bng.ddaja.common.hateoas.CommonHateoas;

import lombok.Getter;

@Getter
public enum UserHateoas implements CommonHateoas {
    
    SELF("GET", "https://api.ddaja.com/users/")
    , UPDATE("PATCH", "https://api.ddaja.com/users/")
    , REPLACE("PUT", "https://api.ddaja.com/users/")
    , DELETE("DELETE", "https://api.ddaja.com/users/");

    private String method;
    private String url;

    UserHateoas(String method, String url) {
        this.method = method;
        this.url = url;
    }

    @Override
    public Link initLink(long id) {
        return new Link(name(), new StringBuilder().append(url).append(Long.toString(id)).toString(), method);
    }
    
}
