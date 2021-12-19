package com.bng.ddaja.question.repository;

import com.bng.ddaja.common.domain.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionRepository extends JpaRepository< Question, Long >, JpaSpecificationExecutor< Question > {
    public Question findById( long id );
}
