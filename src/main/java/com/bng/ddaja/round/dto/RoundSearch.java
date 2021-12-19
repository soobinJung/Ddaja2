package com.bng.ddaja.round.dto;

import lombok.Getter;
import lombok.Setter;

import static com.bng.ddaja.round.spec.RoundSpec.*;
import static org.springframework.data.jpa.domain.Specification.where;

import com.bng.ddaja.common.domain.Round;
import com.bng.ddaja.common.dto.CommonSearch;
import com.bng.ddaja.common.spec.SpecBuilder;

import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class RoundSearch extends CommonSearch {
    
    private Boolean inUse;
    private String examYear;
    private int round;
    private long licenseID;

    @Override
    public Specification<Round> toSpecification(){
        return SpecBuilder.builder(Round.class)
                            .addSpec(where(inUseEqual(inUse)))
                            .addSpec(where(examYearLike(examYear)))
                            .addSpec(where(roundEqual(round)))
                            .addSpec(where(equalLicenseID(licenseID)))
                            .toSpecification();
    }
}
