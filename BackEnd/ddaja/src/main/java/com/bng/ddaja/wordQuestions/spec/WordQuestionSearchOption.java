package com.bng.ddaja.wordQuestions.spec;

import com.bng.ddaja.common.dto.CommonEnumResource;
import com.bng.ddaja.common.enums.CommonEnum;

import lombok.Getter;

@Getter
public enum WordQuestionSearchOption implements CommonEnum {
    
    WORDID("단어 목록 ID", "wordID")
    , INUSE("사용 여부", "inUse");

    private String name;
    private String query;

    WordQuestionSearchOption( String name, String query ){
        this.name = name;
        this.query = query;
    }

    @Override
    public CommonEnumResource toCommonEnumResource(){
        return new CommonEnumResource(name(), name, query);
    }
}
