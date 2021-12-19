package com.bng.ddaja.debate.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bng.ddaja.common.domain.Debate;
import com.bng.ddaja.common.domain.Question;

import org.springframework.data.jpa.domain.Specification;

public class DebateSpec {

    public static Specification<Debate> inUseEqual(final Boolean inUse) {

        return new Specification<Debate>(){
            public Predicate toPredicate(Root<Debate> root, CriteriaQuery<?> query, CriteriaBuilder builder){
                if (inUse == null) return builder.conjunction();
                return builder.equal(root.get("inUse"), inUse);
            }
        };
    }

    public static Specification<Debate> equalQuestionID(final long questionID) {
        return new Specification<Debate>() {
            @Override
            public Predicate toPredicate(Root<Debate> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if(questionID == 0) return builder.conjunction();
                Join<Debate, Question> j = root.join("question", JoinType.INNER);
                return builder.equal(j.get("id"), questionID);
            }
        };
    }
}