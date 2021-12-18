package com.bng.ddaja.common.enums;

import lombok.Getter;

@Getter
public enum TokenType {
    NONE("")
    , KAKAO("카카오")
    , NAVER("네이버")
    , APPLE("애플")
    , GOOGLE("구글");

    private String type;

    TokenType(String type) {
        this.type = type;
    }
}
