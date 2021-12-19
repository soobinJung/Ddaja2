package com.bng.ddaja.common.hateoas.licensesIf;

 
import com.bng.ddaja.common.dto.Link;
import com.bng.ddaja.common.hateoas.CommonHateoas;

public enum LicenseIfHateoas implements CommonHateoas {
    SELF("GET", "https://api.ddaja.com/licenses/")
    , UPDATE("PATCH", "https://api.ddaja.com/licenses/if/")
    , REPLACE("PUT", "https://api.ddaja.com/licenses/if/")
    , DELETE("DELETE", "https://api.ddaja.com/licenses/if/");

    public String method;
    public String url;
    
    LicenseIfHateoas(String method, String url) {
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
