package com.bng.ddaja.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;

import com.bng.ddaja.common.enums.ResultType;
import com.bng.ddaja.common.enums.UserQuestionType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
@Table(name = "TB_USER_QUESTION")
@NoArgsConstructor
@Entity
public class UserQuestion extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UQ_ID")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private UserQuestionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESULT")
    private ResultType result;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESULT_FINAL")
    private ResultType resultFinal;

    @Column(name = "RESULT_SCORE")
    private int resultScore;

    @Column(name = "SOLVED_DATE")
    private String solvedDate;

    @Column(name = "ANSWER")
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "L_ID")
    private License license;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "S_ID")
    private Subject subject;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "R_ID")
    private Round round;

    public void setLicense(License license) {
        if(this.license != null) {
            license.getUserQuestions().remove(this);
        }
        this.license = license;
        if(!license.getUserQuestions().contains(this)) {
            license.setUserQuestion(this);
        }
    }

    public void setUser(User user) {
        if(this.user != null) {
            user.getUserQuestions().remove(this);
        }
        this.user = user;
        if(!user.getUserQuestions().contains(this)) {
            user.setUserQuestion(this);
        }
    }

    public void setSubject(Subject subject) {
        if(this.subject != null) {
            this.subject.getUserQuestions().remove(this);
        }
        this.subject = subject;
        if(subject.getUserQuestions().contains(this)) {
            subject.setUserQuestion(this);
        }
    }

    public void setRound(Round round) {
        if(this.round != null) {
            this.round.getUserQuestions().remove(this);
        }
        this.round = round;
        if(!round.getUserQuestions().contains(this)) {
            round.setUserQuestion(this);
        }
    }
}
