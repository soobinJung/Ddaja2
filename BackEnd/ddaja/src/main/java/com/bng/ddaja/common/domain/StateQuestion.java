package com.bng.ddaja.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bng.ddaja.common.enums.StateQuestionType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
@Table(name = "TB_STATE_QUESTION")
@NoArgsConstructor
@Entity
public class StateQuestion extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SQ_ID")
    private long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name="STATE")
    private StateQuestionType state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "L_ID")
    private License license;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="S_ID")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="R_ID")
    private Round round;

    @ManyToOne
    @JoinColumn(name="Q_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "U_ID")
    private User user;

    public void setLicense(License license) {
        if(this.license != null){
            this.license.getStateQuestions().remove(this);
        }
        this.license = license;
        if(!license.getStateQuestions().contains(this)) {
            license.setStateQuestion(this);
        }
    }

    public void setSubject(Subject subject){
        if(this.subject != null){
            this.subject.getStateQuestions().remove(this);
        }
        this.subject = subject;
        if(!subject.getStateQuestions().contains(this)) {
            subject.setStateQuestion(this);
        }
    }

    public void setRound(Round round){
        if(this.round != null){
            this.round.getStateQuestions().remove(this);
        }
        this.round = round;
        if(!round.getStateQuestions().contains(this)) {
            round.setStateQuestion(this);
        }
    }

    public void setQuestion(Question question){
        if(this.question != null){
            this.question.getStateQuestions().remove(this);
        }
        this.question = question;
        if(!question.getStateQuestions().contains(this)) {
            question.setStateQuestion(this);
        }
    }

    public void setUser(User user){
        if(this.user != null){
            this.user.getStateQuestions().remove(this);
        }
        this.user = user;
        if(!user.getStateQuestions().contains(this)) {
            user.setStateQuestion(this);
        }
    }
}
