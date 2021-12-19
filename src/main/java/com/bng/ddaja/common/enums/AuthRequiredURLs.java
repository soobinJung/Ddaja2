package com.bng.ddaja.common.enums;

import lombok.Getter;

@Getter
public enum AuthRequiredURLs {
    NONE("none");

    private String name;

    AuthRequiredURLs(String name) {
        this.name = name;
    }
}
