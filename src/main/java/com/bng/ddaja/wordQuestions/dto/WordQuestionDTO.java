package com.bng.ddaja.wordQuestions.dto;

import com.bng.ddaja.common.domain.Word;
import com.bng.ddaja.common.domain.WordQuestion;
import com.bng.ddaja.common.dto.CommonDTO;
import com.bng.ddaja.words.dto.WordDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class WordQuestionDTO extends CommonDTO{

    private long id;
    private String content;
    private String answer;
    private boolean inUse;
    private long lID;
    private long wID;
    private WordDTO wordDTO;


    public WordQuestionDTO( WordQuestion wordQuestion ){
        id      = wordQuestion.getId();
        wordDTO = new WordDTO(wordQuestion.getWord());
        inUse   = wordQuestion.isInUse();
        lID     = wordQuestion.getLID();
        content = wordQuestion.getContent();
        answer  = wordQuestion.getAnswer();
    }

    public WordQuestion toEntity(Word word){ 
        return WordQuestion.builder()
        .id(id)
        .word(word)
        .inUse(inUse)
        .lID(lID)
        .content(content)
        .answer(answer)
        .build();
    }

    public void checkoutValue(){
        log.info("====== WordQuestion ToString ====== ");
        log.info("ID         : " + this.id);
        log.info("LICENSE ID : " + this.lID);
        log.info("WORD ID    : " + this.wID);
        log.info("CONTENT    : " + this.content);
        log.info("ANSWER     : " + this.answer);
        log.info("===================================="); 
    }
}
