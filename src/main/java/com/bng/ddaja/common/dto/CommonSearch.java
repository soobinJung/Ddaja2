package com.bng.ddaja.common.dto;

import com.bng.ddaja.common.domain.CommonEntity;
import com.bng.ddaja.common.spec.CommonSpec;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommonSearch implements CommonSpec {

    private int page = 0;

    private int size = 10;

    @Override
    public Pageable toPageable() {
        return PageRequest.of(page == 0 ? page : page - 1, size);
    }

    @Override
    public Specification<? extends CommonEntity> toSpecification() {
        return null;
    }
}
