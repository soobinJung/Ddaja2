package com.bng.ddaja.round.repository;

import com.bng.ddaja.common.domain.Round;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoundRepository extends JpaRepository<Round, Long>, JpaSpecificationExecutor<Round> {
    public Round findById(long id);
}
