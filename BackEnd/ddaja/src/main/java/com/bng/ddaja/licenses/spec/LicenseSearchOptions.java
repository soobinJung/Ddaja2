package com.bng.ddaja.licenses.spec;

import com.bng.ddaja.common.dto.CommonEnumResource;
import com.bng.ddaja.common.enums.CommonEnum;

import lombok.Getter;

@Getter
public enum LicenseSearchOptions implements CommonEnum {

    NAME("자격증 이름", "name")
    , SUBJECTNAME("과목 이름", "subjectName")
    , INUSE("사용 여부", "inUse");

    private String name;
    private String query;
    
    LicenseSearchOptions(String name, String query) {
        this.name = name;
        this.query = query;
    }

    @Override
    public CommonEnumResource toCommonEnumResource() {
        return new CommonEnumResource(name(), name, query);
    }
}