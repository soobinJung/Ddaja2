package com.bng.ddaja.common.spec;

import com.bng.ddaja.common.domain.CommonEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CommonSpec {
    
    public Pageable toPageable();
    
    public Specification<? extends CommonEntity> toSpecification();
    
}
