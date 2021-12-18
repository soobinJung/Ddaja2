package com.bng.ddaja.successComment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.bng.ddaja.common.domain.SuccessComment;

public interface SuccessCommentRepository extends JpaRepository< SuccessComment, Long >, JpaSpecificationExecutor< SuccessComment >{  
    public SuccessComment findById( long id );
}
