package com.bng.ddaja.licenseIf.dto;

import lombok.Getter;
import lombok.Setter;

import static com.bng.ddaja.licenseIf.spec.LicenseIfSpec.*;
import static org.springframework.data.jpa.domain.Specification.where;

import com.bng.ddaja.common.domain.LicenseIf;
import com.bng.ddaja.common.dto.CommonSearch;
import com.bng.ddaja.common.spec.SpecBuilder;

import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class LicenseIfSearch extends CommonSearch {
    
    private String name;
    private long licenseID;
    private Boolean inUse;

    @Override
    public Specification<LicenseIf> toSpecification(){
        return SpecBuilder.builder(LicenseIf.class)
                            .addSpec(where(inUseEqual(inUse)))
                            .addSpec(where(equalLicenseID(licenseID)))
                            .toSpecification();
    }
}
