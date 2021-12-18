package com.bng.ddaja.licenses.dto;

import lombok.Getter;
import lombok.Setter;

import static com.bng.ddaja.licenses.spec.LicenseSpec.*;
import static org.springframework.data.jpa.domain.Specification.where;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.dto.CommonSearch;
import com.bng.ddaja.common.spec.SpecBuilder;

import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class LicenseSearch extends CommonSearch {
    
    private String subjectName;
    private String name;
    private Boolean inUse;

    @Override
    public Specification<License> toSpecification() {
        return SpecBuilder.builder(License.class)
                            .addSpec(where(subjectNameLike(subjectName)))
                            .addSpec(where(nameLike(name)))
                            .addSpec(where(inUseEqual(inUse)))
                .toSpecification();
    }
} 