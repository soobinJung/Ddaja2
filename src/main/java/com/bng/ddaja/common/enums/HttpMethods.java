package com.bng.ddaja.common.enums;

import lombok.Getter;

@Getter
public enum HttpMethods {
    GET("get"), POST("post"), PUT("put"), PATCH("patch"), DELETE("delete"), OPTIONS("options");

    private String name;

    HttpMethods(String name) {
        this.name = name;
    }
}
