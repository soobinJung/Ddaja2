package com.bng.ddaja.words.spec;

import com.bng.ddaja.common.dto.CommonEnumResource;
import com.bng.ddaja.common.enums.CommonEnum;

import lombok.Getter;

@Getter
public enum WordSearchOptions implements CommonEnum{

    NAME("단어 목록명", "name")
    , LICENSEID("자격증 ID", "licenseID")
    , INUSE("사용 여부", "inUse");

    private String name;
    private String query;

    WordSearchOptions( String name, String query ){
        this.name = name;
        this.query = query;
    }

    @Override
    public CommonEnumResource toCommonEnumResource(){
        return new CommonEnumResource(name(), name, query);
    }
}