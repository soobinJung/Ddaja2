package com.bng.ddaja.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TB_DEBATE")
@Entity
public class Debate extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "D_ID")
    private long id;

    @Column(name = "DP_ID")
    private long dpID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Q_ID")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_ID")
    private User user;

    @Column(name = "DEBATE")
    private String debate;

    @Column(name = "LIKE_COUNT")
    private long likeCount;

    @Column(name = "DISLIKE_COUNT")
    private long dislikeCount;

    @Column(name="IN_USE")
    private String inUse;
    
    public void setQuestion(Question question) {
        if(this.question != null) {
            question.getDebates().remove(this);
        }
        this.question = question;
        if(!question.getDebates().contains(this)) {
            question.setDebate(this);
        }
    }

    public void setUser(User user) {
        if(this.user != null) {
            user.getDebates().remove(this);
        }
        this.user = user;
        if(!user.getDebates().contains(this)) {
            user.setDebate(this);
        }
    }
}
