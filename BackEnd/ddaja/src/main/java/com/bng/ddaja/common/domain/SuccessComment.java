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
@Table(name="TB_SUCCESS_COMMENT")
@Entity
public class SuccessComment extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SC_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "L_ID")
    private License license;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_ID")
    private User user;
    
    @Column(name = "IN_USE")
    private boolean inUse;

    @Column(name = "SUCCESS_COMMENT")
    private String successComment;

    @Column(name = "SUCCESS_TITLE")
    private String successTitle;

    @Column(name = "SUCCESS_SCORE")
    private int successScore;

    @Column(name = "LIKE_COUNT")
    private int likeCount;

    public void setLicense( License license ){
        if(this.license != null){
            this.license.getSuccessComments().remove(this);
        }
        this.license = license;

        if(!license.getSuccessComments().contains(this)) {
            license.setSuccessComments(this);
        }
    }

    public void setUser( User user ){
        if(this.user != null){
            this.user.getSuccessComments().remove(this);
        }
        this.user = user;

        if(!user.getSuccessComments().contains(this)) {
            user.setSuccessComments(this);
        }
    }
}