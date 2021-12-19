package com.bng.ddaja.common.enums;

import lombok.Getter;

@Getter
public enum AdminRole {

    NONE("임시 관리자")
    , SUPER("슈퍼 관리자")
    , NOMAL("일반 관리자");

    private String role;

    AdminRole(String role) {
        this.role = role;
    }
}
