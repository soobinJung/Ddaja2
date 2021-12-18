package com.bng.ddaja.round.spec;

import com.bng.ddaja.common.dto.CommonEnumResource;
import com.bng.ddaja.common.enums.CommonEnum;

import lombok.Getter;

@Getter
public enum RoundSearchOptions implements CommonEnum {
    
    EXAM_YEAR("응시 년도", "examYear")
    , ROUND("응시 회차", "round")
    , INUSE("사용 여부", "inUse")
    ;

    private String name;
    private String query;

    RoundSearchOptions(String name, String query){
        this.name = name;
        this.query = query;
    }

    @Override
    public CommonEnumResource toCommonEnumResource() {
        return new CommonEnumResource(name(), name, query);
    }
}
