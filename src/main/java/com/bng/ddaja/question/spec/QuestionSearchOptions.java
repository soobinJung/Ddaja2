package com.bng.ddaja.question.spec;

import com.bng.ddaja.common.dto.CommonEnumResource;
import com.bng.ddaja.common.enums.CommonEnum;

import lombok.Getter;

@Getter
public enum QuestionSearchOptions implements CommonEnum {
    
    INUSE("사용 여부", "inUse");

    private String name;
    private String query;
    
    QuestionSearchOptions(String name, String query) {
        this.name = name;
        this.query = query;
    }

    @Override
    public CommonEnumResource toCommonEnumResource() {
        return new CommonEnumResource(name(), name, query);
    }
}
