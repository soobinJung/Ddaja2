package com.bng.ddaja.common.enums;

import com.bng.ddaja.common.dto.CommonEnumResource;

import lombok.Getter;

@Getter
public enum LicenseCode implements CommonEnum {

    NONE("")
    , IP_GISA("정보처리 기사")
    , IP_SANGISA("정보처리 산업기사")
    , CS_ONE("컴퓨터활용능력 1급")
    , CS_TWO("컴퓨터활용능력 2급")
    , LM_ONE("리눅스마스터 1급");

    private String name;

    LicenseCode(String name) {
        this.name = name;
    }

    @Override
    public CommonEnumResource toCommonEnumResource() {
        return new CommonEnumResource(name(), name);
    }
}