package com.bng.ddaja.debate.dto;

import java.util.Date;

import com.bng.ddaja.common.domain.Debate;
import com.bng.ddaja.common.domain.Question;
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
public class DebateDTO extends CommonDTO {
    
    private long id;
    private long dpID;
    private long qID;
    private long uID;
    private String debate;
    private long likeCount;
    private long dislikeCount;
    private String inUse;
    private Date createdDate;

    public DebateDTO( Debate vo ){
        this.id           = vo.getId();
        this.dpID         = vo.getDpID();
        this.qID          = vo.getQuestion().getId();
        this.debate       = vo.getDebate();
        this.likeCount    = vo.getLikeCount();
        this.inUse        = vo.getInUse();
        this.dislikeCount = vo.getDislikeCount();
        this.createdDate  = vo.getCreatedDate();
    }

    public Debate toEntity(Question question){
        return Debate.builder()
                        .id(id)
                        .dpID(dpID)
                        .question(question)
                        .debate(debate)
                        .likeCount(likeCount)
                        .dislikeCount(dislikeCount)
                        .inUse(inUse)
                        .build();
    }

    public void checkValue() {
        log.info("====== Debate ToString ====== ");
        log.info("ID             : " + this.id);
        log.info("QPID           : " + this.dpID);
        log.info("QID            : " + this.qID);
        log.info("DEBATE         : " + this.debate );
        log.info("LIKE COUNT     : " + this.likeCount);
        log.info("DISLIKE COUNT  : " + this.dislikeCount);
        log.info("====================================");
    }
}
