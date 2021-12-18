package com.bng.ddaja.common.hateoas.successComment;


import com.bng.ddaja.common.dto.Link;
import com.bng.ddaja.common.hateoas.CommonHateoas;
public enum SuccessCommentHateoas implements CommonHateoas {
    
    SELF ("GET", "https://api.ddaja.com/success-comment/")
    , UPDATE ("PATCH", "https://api.ddaja.com/success-comment/")
    ;

    public String method;
    public String url;

    SuccessCommentHateoas (String method, String url){
        this.method = method;
        this.url = url;
    }

    @Override
    public Link initLink(long id){
        return new Link(name(), new StringBuilder().append(url).append(Long.toString(id)).toString(), method);
    }
}
