package com.bng.ddaja.common.enums;

import com.bng.ddaja.common.dto.CommonEnumResource;

import lombok.Getter;

@Getter
public enum Roles implements CommonEnum {
    ADMIN("관리자"), USER("사용자"), VISITOR("방문자");

    private String name;

    Roles(String name) {
        this.name = name;
    }

    @Override
    public CommonEnumResource toCommonEnumResource() {
        return new CommonEnumResource(name(), name);
    }
    
}
