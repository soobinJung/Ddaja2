package com.bng.ddaja.successComment.dto;

import lombok.Getter;
import lombok.Setter;

import static com.bng.ddaja.successComment.spec.SuccessCommentSpec.*;
import static org.springframework.data.jpa.domain.Specification.where;

import com.bng.ddaja.common.domain.SuccessComment;
import com.bng.ddaja.common.dto.CommonSearch;
import com.bng.ddaja.common.spec.SpecBuilder;

import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class SuccessCommentSearch extends CommonSearch {
    
    private String name;
    private long licenseID;
    private Boolean inUse;

    @Override
    public Specification<SuccessComment> toSpecification(){
        return SpecBuilder.builder(SuccessComment.class)
                            .addSpec(where(inUseEqual(inUse)))
                            .addSpec(where(equalLicenseID(licenseID)))
                            .toSpecification();
    }
}
