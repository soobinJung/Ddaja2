package com.bng.ddaja.common.hateoas.word;

import com.bng.ddaja.common.dto.Link;
import com.bng.ddaja.common.hateoas.CommonHateoas;

public enum WordHateoas implements CommonHateoas {

    SELF ("GET", "https://api.ddaja.com/word/")
    , UPDATE ("PATCH", "https://api.ddaja.com/word/")
    ;

    public String method;
    public String url;

    WordHateoas (String method, String url){
        this.method = method;
        this.url = url;
    }

    @Override
    public Link initLink(long id){
        return new Link(name(), new StringBuilder().append(url).append(Long.toString(id)).toString(), method);
    }
}
