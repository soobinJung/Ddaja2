package com.bng.ddaja.common.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "TB_USER")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "U_ID")
    private long id;
    
    @Column(name = "ID")
    private String userId;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "EMAIL")
    private String eMail;

    @Column(name = "PROFILE_IMG")
    private String profileImg;

    @OneToMany(mappedBy = "user")
    private List<UserQuestion> userQuestions;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "user")
    private List<StateQuestion> stateQuestions;

    @OneToMany(mappedBy = "user")
    private List<Debate> debates;

    @OneToMany(mappedBy = "user")
    private List<SuccessComment> successComments;

    public void setUserQuestion(UserQuestion userQuestion) {
        this.userQuestions.add(userQuestion);
        if(userQuestion.getUser() != this) {
            userQuestion.setUser(this);
        }
    }

    public void setToken(Token token) {
        this.tokens.add(token);
        if(token.getUser() != this) {
            token.setUser(this);
        }
    }

    public void setStateQuestion(StateQuestion stateQuestion){
        this.stateQuestions.add(stateQuestion);
        if(stateQuestion.getUser() != this) {
            stateQuestion.setUser(this);
        }
    }

    public void setDebate(Debate debate) {
        this.debates.add(debate);
        if(debate.getUser() != this) {
            debate.setUser(this);
        }
    } 

    public void setSuccessComments(SuccessComment successComment) {
        this.successComments.add(successComment);
        if(successComment.getUser() != this) {
            successComment.setUser(this);
        }
    }
}
