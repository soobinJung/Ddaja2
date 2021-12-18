package com.bng.ddaja.wordQuestions.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bng.ddaja.common.domain.Word;
import com.bng.ddaja.common.domain.WordQuestion;

import org.springframework.data.jpa.domain.Specification;

public class WordQuestionSpec {

    public static Specification<WordQuestion> equalWordID(final long wordID){
        return new Specification<WordQuestion>(){
            public Predicate toPredicate(Root<WordQuestion> root, CriteriaQuery<?> query, CriteriaBuilder builder){
                if(wordID == 0) return builder.conjunction();
                Join<WordQuestion, Word> j = root.join("word", JoinType.INNER);
                return builder.equal(j.get("id"), wordID);
            }
        };
    }

    public static Specification<WordQuestion> inUseEqual(final Boolean inUse){
        return new Specification<WordQuestion>() {
            public  Predicate toPredicate(Root<WordQuestion> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (inUse == null) return builder.conjunction();
                return builder.equal(root.get("inUse"), inUse);
            }
        };
    }
}