package com.bng.ddaja.test.dto;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.enums.LicenseCode;
import com.bng.ddaja.common.enums.LicenseType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LicenseDTO {
    
    private long id;
    private LicenseCode lCode;
    private String name;
    private boolean inUse;
    private String agency;
    private int passScore; 
    private LicenseType type;

    public LicenseDTO ( long id, String name ) {
        this.id = id;
        this.name = name;
    }
    
    public LicenseDTO ( License license ){
        this.id = license.getId();
        this.lCode = license.getLCode();
        this.passScore = license.getPassScore();
        this.name = license.getName();
        this.agency = license.getAgency();
        this.type = license.getType();
    }

    public License toEntity (){
        return License.builder()
                        .id(id)
                        .lCode(lCode)
                        .name(name)
                        .inUse(inUse)
                        .passScore(passScore)
                        .agency(agency)
                        .type(type)
                        .build();
    }
}
