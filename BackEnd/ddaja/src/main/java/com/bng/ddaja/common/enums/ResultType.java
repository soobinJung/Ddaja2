package com.bng.ddaja.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultType {
    
    NONE("")
    , PASS("합격")
    , FAIL("불합격");

    private String result;
}
