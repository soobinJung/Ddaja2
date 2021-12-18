package com.bng.ddaja.successComment.dto;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.domain.SuccessComment;
import com.bng.ddaja.common.domain.User;
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
public class SuccessCommentDTO extends CommonDTO {

    private long id;
    private long lID;
    private long uID;
    private boolean inUse;
    private String successComment;
    private String successTitle;
    private int likeCount;
    private int successScore;

    public SuccessCommentDTO( SuccessComment vo ){
        this.id             = vo.getId();
        this.lID            = vo.getLicense().getId();
        this.uID            = vo.getUser().getId();
        this.successTitle   = vo.getSuccessTitle();
        this.successComment = vo.getSuccessComment();
        this.likeCount      = vo.getLikeCount();
        this.inUse          = vo.isInUse();
        this.successScore   = vo.getSuccessScore();
        super.createdDate   = vo.getCreatedDate();
    }

    public SuccessComment toEntity(License license, User user){
        return SuccessComment.builder()
        .id(this.id)
        .license(license)
        .user(user)
        .successTitle(this.successTitle)
        .successComment(this.successComment)
        .likeCount(this.likeCount)
        .inUse(this.inUse)
        .successScore(this.successScore)
        .build();
    }

    public void checkValue() {
        log.info("====== SuccessCommentDTO ToString ====== ");
        log.info("ID             : " + this.id);
        log.info("lID            : " + this.lID);
        log.info("uID            : " + this.uID);
        log.info("IN USE         : " + this.inUse);
        log.info("TITLE          : " + this.successTitle);
        log.info("COMMENT        : " + this.successComment);
        log.info("SUCCESS SCORE  : " + this.successScore);
        log.info("LIKE           : " + this.likeCount);
        log.info("====================================");
    }
}