package com.bng.ddaja.debate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bng.ddaja.common.domain.Debate;

public interface DebateRepository extends JpaRepository< Debate, Long >, JpaSpecificationExecutor< Debate >{  
    public Debate findById( long id );
}
