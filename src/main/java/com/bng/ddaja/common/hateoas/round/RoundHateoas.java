package com.bng.ddaja.common.hateoas.round;

import com.bng.ddaja.common.dto.Link;
import com.bng.ddaja.common.hateoas.CommonHateoas;

public enum RoundHateoas implements CommonHateoas {
    SELF("GET", "https://api.ddaja.com/round/")
    , UPDATE("PATCH", "https://api.ddaja.com/round/")
    , REPLACE("PUT", "https://api.ddaja.com/round/")
    , DELETE("DELETE", "https://api.ddaja.com/round/");

    public String method;
    public String url;
    
    RoundHateoas(String method, String url) {
        this.method = method;
        this.url = url;
    }

    @Override
    public Link initLink(long id) {
        return new Link(
            name()
            , new StringBuilder().append(url).append(Long.toString(id)).toString()
            , method
        );
    } 
}
