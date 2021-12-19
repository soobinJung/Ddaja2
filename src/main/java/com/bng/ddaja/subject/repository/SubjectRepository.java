package com.bng.ddaja.subject.repository;

import com.bng.ddaja.common.domain.Subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor< Subject >{
    public Subject findById( long id );
}
