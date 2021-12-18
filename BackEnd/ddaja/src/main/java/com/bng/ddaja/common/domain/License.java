package com.bng.ddaja.common.domain; 

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bng.ddaja.common.enums.LicenseCode;
import com.bng.ddaja.common.enums.LicenseType;

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
@Table(name="TB_LICENSE")
@Entity
public class License extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "L_ID")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "L_CODE")
    private LicenseCode lCode;
    
    @Column(name = "NAME")
    private String name;

    @Column(name = "IN_USE")
    private boolean inUse;

    @Column(name = "AGENCY")
    private String agency;

    @Column(name = "PASS_SCORE")
    private int passScore;

    @Column(name = "EXAM_FEE")
    private int examFee;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private LicenseType type;

    @OneToMany(mappedBy = "license", fetch = FetchType.LAZY)
    private List<Word> words; 

    @OneToMany(mappedBy = "license")
    private List<Round> rounds;

    @OneToMany(mappedBy = "license")
    private List<UserQuestion> userQuestions;

    @OneToMany(mappedBy = "license")
    private List<Question> questions;

    @OneToMany(mappedBy = "license")
    private List<Subject> subjects;

    @OneToMany(mappedBy = "license")
    private List<OpenAPI> openAPIs;

    @OneToMany(mappedBy = "license")
    private List<LicenseInfo> licenseInfos;

    @OneToMany(mappedBy = "license")
    private List<StateQuestion> stateQuestions;

    @OneToMany(mappedBy = "license")
    private List<LicenseIf> licenseIfs;
    
    @OneToMany(mappedBy = "license")
    private List<SuccessComment> successComments;

    public void setWord(Word word) {
        this.words.add(word);
        if (word.getLicense() != this) {
            word.setLicense(this);
        }
    }

    public void setRound(Round round) {
        this.rounds.add(round);
        if (round.getLicense() != this) {
            round.setLicense(this);
        }
    }

    public void setUserQuestion(UserQuestion userQuestion) {
        this.userQuestions.add(userQuestion);
        if (userQuestion.getLicense() != this) {
            userQuestion.setLicense(this);
        }
    }

    public void setSubject(Subject subject) {
        this.subjects.add(subject);
        if (subject.getLicense() != this) {
            subject.setLicense(this);
        }
    }

    public void setQuestion(Question question) {
        this.questions.add(question);
        if(question.getLicense() != this) {
            question.setLicense(this);
        }
    }

    public void setOpenAPI(OpenAPI openAPI) {
        this.openAPIs.add(openAPI);
        if(openAPI.getLicense() != this) {
            openAPI.setLicense(this);
        }
    }
    
    public void setLicenseInfo(LicenseInfo licenseInfo) {
        this.licenseInfos.add(licenseInfo);
        if(licenseInfo.getLicense() != this) {
            licenseInfo.setLicense(this);
        }
    }

    public void setStateQuestion(StateQuestion stateQuestion) {
        this.stateQuestions.add(stateQuestion);
        if(stateQuestion.getLicense() != this){
            stateQuestion.setLicense(this);
        }
    }

    public void setLicenseIfs(LicenseIf licenseIf) {
        this.licenseIfs.add(licenseIf);
        if(licenseIf.getLicense() != this){
            licenseIf.setLicense(this);
        }
    }

    public void setSuccessComments(SuccessComment successComment) {
        this.successComments.add(successComment);
        if(successComment.getLicense() != this){
            successComment.setLicense(this);
        }
    }
}