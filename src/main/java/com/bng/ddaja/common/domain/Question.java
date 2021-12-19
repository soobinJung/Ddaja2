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
@Table(name = "TB_QUESTION")
@Entity
public class Question extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Q_ID")
    private long id;
    
    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENT")
    private String content;

    @Column(name="SCORE")
    private int score;

    @Column(name="NO")
    private int no;

    @Column(name="ANSWER")
    private int answer;

    @Column(name="ANSWER_ONE")
    private String answerOne;

    @Column(name="ANSWER_TWO")
    private String answerTwo;

    @Column(name="ANSWER_THR")
    private String answerThr;

    @Column(name="ANSWER_FOUR")
    private String answerFour;

    @Column(name="ANSWER_FIVE")
    private String answerFive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "L_ID")
    private License license;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "S_ID")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "R_ID")
    private Round round;
    
    @Column(name = "IN_USE")
    private boolean inUse;

    @OneToMany(mappedBy = "question")
    private List<StateQuestion> stateQuestions;

    @OneToMany(mappedBy = "question")
    private List<Debate> debates;

    public void setSubject(Subject subject) {
        if(this.subject != null) {
            this.subject.getQuestions().remove(this);
        }
        this.subject = subject;
        if(!subject.getQuestions().contains(this)) {
            subject.setQuestion(this);
        }
    }

    public void setRound(Round round) {
        if(this.round != null) {
            this.round.getQuestions().remove(this);
        }
        this.round = round;
        if(!round.getQuestions().contains(this)) {
            round.setQuestion(this);
        }
    }

    public void setLicense(License license) {
        if(this.license != null) {
            this.license.getQuestions().remove(this);
        }
        this.license = license;
        if(!license.getQuestions().contains(this)) {
            license.setQuestion(this);
        }
    }

    public void setStateQuestion(StateQuestion stateQuestion){
        this.stateQuestions.add(stateQuestion);
        if(stateQuestion.getQuestion() != this) {
            stateQuestion.setQuestion(this);
        }
    }

    public void setDebate(Debate debate) {
        this.debates.add(debate);
        if(debate.getQuestion() != this) {
            debate.setQuestion(this);
        }
    }
}
