package com.bng.ddaja.debate.dto;

import lombok.Getter;
import lombok.Setter;

import static com.bng.ddaja.debate.spec.DebateSpec.*;
import static org.springframework.data.jpa.domain.Specification.where;

import com.bng.ddaja.common.domain.Debate;
import com.bng.ddaja.common.dto.CommonSearch;
import com.bng.ddaja.common.spec.SpecBuilder;

import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class DebateSearch extends CommonSearch {

    private long questionID;
    private Boolean inUse;

    @Override
    public Specification<Debate> toSpecification(){
        return SpecBuilder.builder(Debate.class)
                            .addSpec(where(inUseEqual(inUse)))
                            .addSpec(where(equalQuestionID(questionID)))
                            .toSpecification();
    }
}
