package com.bng.ddaja.test.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestParameter {
    
    private String[] q;

    private String[] fields;

    @NotNull(message = "page는 필수 값입니다.")
    private int page;

    @Max(value = 100, message = "최대 데이터 제한은 100 입니다.")
    private int limit = 10;
}
