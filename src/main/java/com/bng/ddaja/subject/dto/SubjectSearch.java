package com.bng.ddaja.subject.dto;

import lombok.Getter;
import lombok.Setter;

import static com.bng.ddaja.subject.spec.SubjectSpec.*;
import static org.springframework.data.jpa.domain.Specification.where;

import com.bng.ddaja.common.domain.Subject;
import com.bng.ddaja.common.dto.CommonSearch;
import com.bng.ddaja.common.spec.SpecBuilder;

import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class SubjectSearch extends CommonSearch {

    private String name;
    private Boolean inUse;

    @Override
    public Specification<Subject> toSpecification(){
        return SpecBuilder.builder(Subject.class)
                            .addSpec(where(nameLike(name)))
                            .addSpec(where(inUseEqual(inUse)))
                            .toSpecification();
    }
}
