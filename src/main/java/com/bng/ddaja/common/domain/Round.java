package com.bng.ddaja.common.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false, of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ROUND")
@Entity
public class Round extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="R_ID")
    private long id;

    @Column(name="EXAM_DATE")
    private String examDate;

    @Column(name="EXAM_YEAR")
    private String examYear;

    @Column(name="ROUND")
    private int round;

    @Column(name = "IN_USE")
    private boolean inUse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "L_ID")
    private License license;

    @OneToMany(mappedBy = "round", fetch = FetchType.LAZY)
    private List<UserQuestion> userQuestions;

    @OneToMany(mappedBy = "round", fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToMany(mappedBy = "round", fetch = FetchType.LAZY)
    private List<OpenAPI> openAPIs;

    @OneToMany(mappedBy = "round", fetch = FetchType.LAZY)
    private List<StateQuestion> stateQuestions;

    public void setLicense(License license) {
        if(this.license != null) {
            this.license.getRounds().remove(this);
        }
        this.license = license;
        if(!license.getRounds().contains(this)) {
            license.setRound(this);
        }
    }

    public void setUserQuestion(UserQuestion userQuestion) {
        this.userQuestions.add(userQuestion);
        if(userQuestion.getRound() != this) {
            userQuestion.setRound(this);
        }
    }

    public void setQuestion(Question question) {
        this.questions.add(question);
        if(question.getRound() != this) {
            question.setRound(this);
        }
    }

    public void setOpenAPI(OpenAPI openAPI) {
        this.openAPIs.add(openAPI);
        if(openAPI.getRound() != this) {
            openAPI.setRound(this);
        }
    }

    public void setStateQuestion(StateQuestion stateQuestion){
        this.stateQuestions.add(stateQuestion);
        if(stateQuestion.getRound() != this) {
            stateQuestion.setRound(this);
        }
    }
}
