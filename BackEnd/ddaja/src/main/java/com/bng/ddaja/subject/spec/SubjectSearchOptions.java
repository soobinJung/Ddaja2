package com.bng.ddaja.subject.spec;

import com.bng.ddaja.common.dto.CommonEnumResource;
import com.bng.ddaja.common.enums.CommonEnum;

import lombok.Getter;

@Getter
public enum SubjectSearchOptions implements CommonEnum {
    
    NAME("과목 이름", "name")
    , INUSE("사용 여부", "inUse");

    private String name;
    private String query;

    SubjectSearchOptions(String name, String query){
        this.name = name;
        this.query = query;
    }

    @Override
    public CommonEnumResource toCommonEnumResource(){
        return new CommonEnumResource(name(), name, query);
    }
}