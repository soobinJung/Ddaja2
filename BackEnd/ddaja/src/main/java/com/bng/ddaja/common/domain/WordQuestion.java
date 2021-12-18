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
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false, of = "id")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_WORD_QUESTION")
@Entity
public class WordQuestion extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="WQ_ID")
    private long id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name="IN_USE")
    private boolean inUse;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "W_ID")
    private Word word;

    @Column(name = "L_ID")
    private long lID;

    public void setWord(Word word) {
        if(this.word != null) {
            this.word.getWordQuestions().remove(this);
        }
        this.word = word;
        if(word.getWordQuestions() != null){
            if(!word.getWordQuestions().contains(this)) {
                word.setWordQuestion(this);
            }
        }
    }
}