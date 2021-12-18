package com.bng.ddaja.licenseIf.spec;

import com.bng.ddaja.common.dto.CommonEnumResource;
import com.bng.ddaja.common.enums.CommonEnum;

import lombok.Getter;

@Getter
public enum LicenseIfSearchOptions implements CommonEnum {
    
    NAME("이름", "name");

    private String name;
    private String query;

    LicenseIfSearchOptions(String name, String query){
        this.name = name;
        this.query = query;
    }

    @Override
    public CommonEnumResource toCommonEnumResource(){
        return new CommonEnumResource(name(), name, query);
    }
}
