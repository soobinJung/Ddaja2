package com.bng.ddaja.subject.dto;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.domain.Subject;
import com.bng.ddaja.common.dto.CommonDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO extends CommonDTO {

    private long id;
    private String name;
    private int minScore;
    private boolean inUse;
    private long lID;
    
    public SubjectDTO(Subject subject) {
        id       = subject.getId();
        lID      = subject.getLicense().getId();
        name     = subject.getName();
        minScore = subject.getMinScore();
        inUse    = subject.isInUse();
    }

    public Subject toEntity(License license){
        return Subject.builder()
        .id(this.id)
        .license(license)
        .name(this.name)
        .minScore(this.minScore)
        .inUse(this.inUse)
        .build();
    }

    public void checkoutValue(){
        log.info("====== SubjectDTO ToString ====== ");
        log.info("ID         : " + this.id);
        log.info("LICENSE ID : " + this.lID);
        log.info("NAME       : " + this.name);
        log.info("MIN SCORE  : " + this.minScore);
        log.info("IN USE     : " + this.inUse);
        log.info("===================================="); 
    }
}