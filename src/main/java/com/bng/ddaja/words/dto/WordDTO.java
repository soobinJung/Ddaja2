package com.bng.ddaja.words.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.domain.Word;
import com.bng.ddaja.common.domain.WordQuestion;
import com.bng.ddaja.common.dto.CommonDTO;
import com.bng.ddaja.licenses.dto.LicenseDTO;
import com.bng.ddaja.wordQuestions.dto.WordQuestionDTO;

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
public class WordDTO extends CommonDTO { 

    private long id;
    private long lID;
    private String title;
    private boolean inUse;
    private LicenseDTO license;
    private List<WordQuestionDTO> wordQuestions = new ArrayList<WordQuestionDTO>();
    private int wordQuestionsCount;

    public WordDTO( Word word ){
        id                 = word.getId();
        title              = word.getTitle();
        inUse              = word.isInUse();
        createdDate        = word.getCreatedDate();
        modifiedDate       = word.getModifiedDate();
        license            = new LicenseDTO(word.getLicense());

        Optional<List<WordQuestion>> op = Optional.ofNullable(word.getWordQuestions());
        if(op.isPresent()){
            op.get().forEach( x -> {
                if(x.isInUse()){
                    wordQuestionsCount++;
                }
            });
        }else{
            wordQuestionsCount = 0;
        }
    }

    public Word toEntity( License license ){
        return Word.builder()
            .id(id)
            .inUse(inUse)
            .title(title)
            .license(license)
            .build();
    }

    public void checkValue() {
        log.info("====== WordDTO ToString ====== ");
        log.info("ID        : " + this.id);
        log.info("lID       : " + this.lID);
        log.info("TITLE     : " + this.title);
        log.info("IN USE    : " + this.inUse);
        log.info("====================================");
    }
}
