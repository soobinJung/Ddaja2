package com.bng.ddaja.common.enums;

import com.bng.ddaja.common.dto.CommonEnumResource;

import lombok.Getter;

@Getter
public enum LicenseType implements CommonEnum {
    NONE("")
    , WRITING("필기")
    , PERFORM("실기");

    private String name;

    LicenseType(String name) {
        this.name = name;
    }

    @Override
    public CommonEnumResource toCommonEnumResource() {
        return new CommonEnumResource(name(), name);
    }
}