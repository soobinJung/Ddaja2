package com.bng.ddaja.common.hateoas.wordQuestion;

import com.bng.ddaja.common.dto.Link;
import com.bng.ddaja.common.hateoas.CommonHateoas;

public enum WordQuestionHateoas implements CommonHateoas {

    SELF ("GET", "https://api.ddaja.com/word-question/")
    , UPDATE ("PATCH", "https://api.ddaja.com/word-question/")
    ;

    public String method;
    public String url;

    WordQuestionHateoas (String method, String url){
        this.method = method;
        this.url = url;
    }

    @Override
    public Link initLink(long id){
        return new Link(name(), new StringBuilder().append(url).append(Long.toString(id)).toString(), method);
    }
}
