package com.bng.ddaja.question.dto;

import lombok.Getter;
import lombok.Setter;

import static com.bng.ddaja.question.spec.QuestionSpec.*;
import static org.springframework.data.jpa.domain.Specification.where;

import com.bng.ddaja.common.domain.Question;
import com.bng.ddaja.common.dto.CommonSearch;
import com.bng.ddaja.common.spec.SpecBuilder;

import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class QuestionSearch extends CommonSearch {
    
    private String name;
    private long RoundID;
    private long licenseID;
    private long SubjectID;
    private Boolean inUse;

    @Override
    public Specification<Question> toSpecification(){
        return SpecBuilder.builder(Question.class)
                            .addSpec(where(inUseEqual(inUse)))
                            .addSpec(where(equalRoundID(RoundID)))
                            .addSpec(where(equalLicenseID(licenseID)))
                            .addSpec(where(equalLicenseID(SubjectID)))
                            .toSpecification();
    }
}
