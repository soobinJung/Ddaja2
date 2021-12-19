package com.bng.ddaja.words.dto;

import com.bng.ddaja.common.domain.Word;
import com.bng.ddaja.common.dto.CommonSearch;
import com.bng.ddaja.common.spec.SpecBuilder;
import static com.bng.ddaja.words.spec.WordSpec.*;
import static org.springframework.data.jpa.domain.Specification.where;

import org.springframework.data.jpa.domain.Specification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordSearch extends CommonSearch {

    private String name;
    private long licenseID;
    private Boolean inUse;

    @Override
    public Specification<Word> toSpecification() {
        return SpecBuilder.builder(Word.class)
                            .addSpec(where(nameLike(name)))
                            .addSpec(where(equalLicenseID(licenseID)))
                            .addSpec(where(inUseEqual(inUse)))
                            .toSpecification();
    } 
}