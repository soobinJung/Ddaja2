package com.bng.ddaja.common.domain;

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
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false, of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_SUBJECT")
@Entity
public class Subject extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="S_ID")
    private long id;
    
    @Column(name="NAME")
    private String name;

    @Column(name="MIN_SCORE")
    private int minScore;

    @Column(name="IN_USE")
    private boolean inUse;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "L_ID")
    private License license;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<UserQuestion> userQuestions;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToMany(mappedBy = "subject")
    private List<StateQuestion> stateQuestions;

    public void setLicense(License license) {
        if(this.license != null) {
            this.license.getSubjects().remove(this);
        }
        this.license = license;
        if(!license.getSubjects().contains(this)) {
            license.setSubject(this);
        }
    }

    public void setUserQuestion(UserQuestion userQuestion) {
        this.userQuestions.add(userQuestion);
        if(userQuestion.getSubject() != this) {
            userQuestion.setSubject(this);
        }
    }

    public void setQuestion(Question question) {
        this.questions.add(question);
        if(question.getSubject() != this) {
            question.setSubject(this);
        }
    }

    public void setStateQuestion(StateQuestion stateQuestion){
        this.stateQuestions.add(stateQuestion);
        if(stateQuestion.getSubject() != this){
            stateQuestion.setSubject(this);
        }
    }
}
