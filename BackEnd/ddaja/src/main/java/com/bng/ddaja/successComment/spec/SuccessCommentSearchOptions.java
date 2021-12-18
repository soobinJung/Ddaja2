package com.bng.ddaja.successComment.spec;

import com.bng.ddaja.common.dto.CommonEnumResource;
import com.bng.ddaja.common.enums.CommonEnum;

import lombok.Getter;

@Getter
public enum SuccessCommentSearchOptions implements CommonEnum {

    NAME("자격증 이름", "name")
    , INUSE("사용 여부", "inUse");

    private String name;
    private String query;
    
    SuccessCommentSearchOptions(String name, String query) {
        this.name = name;
        this.query = query;
    }

    @Override
    public CommonEnumResource toCommonEnumResource() {
        return new CommonEnumResource(name(), name, query);
    }
}
