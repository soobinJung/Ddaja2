package com.bng.ddaja.licenseIf.dto;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.domain.LicenseIf;
import com.bng.ddaja.common.dto.CommonDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class LicenseIfDTO extends CommonDTO {
    
    private long id;
    private long lID;
    private boolean inUse;
    private String licenseIf;

    public LicenseIfDTO(LicenseIf vo) {
        this.id           = vo.getId();
        this.lID          = vo.getLicense().getId();
        this.licenseIf    = vo.getLicenseIf();
        this.inUse        = vo.isInUse();
        super.createdDate = vo.getCreatedDate();
    }

    public LicenseIf toEntity(License license) {
        return LicenseIf.builder()
        .id(this.id)
        .inUse(this.inUse)
        .licenseIf(this.licenseIf)
        .build();
    }

    public void checkValue() {
        log.info("====== LicenseIfDTO ToString ====== ");
        log.info("ID           : " + this.id);
        log.info("LICENSE  ID  : " + this.lID);
        log.info("LICENSE INFO : " + this.licenseIf);
        log.info("IN USE       : " + this.inUse);
        log.info("====================================");
    }
}
