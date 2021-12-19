package com.bng.ddaja.debate.spec;

import com.bng.ddaja.common.dto.CommonEnumResource;
import com.bng.ddaja.common.enums.CommonEnum;

import lombok.Getter;

@Getter
public enum DebateSearchOptions implements CommonEnum  {

    INUSE("사용 여부", "inUse");

    private String name;
    private String query;
    
    DebateSearchOptions(String name, String query) {
        this.name = name;
        this.query = query;
    }

    @Override
    public CommonEnumResource toCommonEnumResource() {
        return new CommonEnumResource(name(), name, query);
    }
}
