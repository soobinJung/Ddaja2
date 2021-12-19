package com.bng.ddaja.common.hateoas.subject;

import com.bng.ddaja.common.dto.Link;
import com.bng.ddaja.common.hateoas.CommonHateoas;

public enum SubjectHateoas implements CommonHateoas {

    SELF ("GET", "https://api.ddaja.com/subject/")
    , UPDATE ("PATCH", "https://api.ddaja.com/subject/")
    ;

    public String method;
    public String url;

    SubjectHateoas (String method, String url){
        this.method = method;
        this.url = url;
    }

    @Override
    public Link initLink(long id){
        return new Link(name(), new StringBuilder().append(url).append(Long.toString(id)).toString(), method);
    }
}
