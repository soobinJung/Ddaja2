package com.bng.ddaja.common.hateoas.question;

import com.bng.ddaja.common.dto.Link;
import com.bng.ddaja.common.hateoas.CommonHateoas;

public enum QuestionHateoas implements CommonHateoas{
    SELF("GET", "https://api.ddaja.com/question/")
    , UPDATE("PATCH", "https://api.ddaja.com/question/")
    , REPLACE("PUT", "https://api.ddaja.com/question/")
    , DELETE("DELETE", "https://api.ddaja.com/question/");

    public String method;
    public String url;
    
    QuestionHateoas(String method, String url) {
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
