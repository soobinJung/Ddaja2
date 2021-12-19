package com.bng.ddaja.question.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.domain.Question;
import com.bng.ddaja.common.domain.Round;
import com.bng.ddaja.common.domain.Subject;

import org.springframework.data.jpa.domain.Specification;

public class QuestionSpec {
    
    public static Specification<Question> inUseEqual(final Boolean inUse){
        return new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if(inUse == null) return builder.conjunction();
                return builder.equal(root.get("inUse"), inUse);
            }
        };
    }

    public static Specification<Question> equalRoundID(final long RoundID){
        return new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if(RoundID == 0) return builder.conjunction();
                Join<Question, Round> j = root.join("round", JoinType.INNER);
                return builder.equal(j.get("id"), RoundID);
            }
        };
    }

    public static Specification<Question> equalLicenseID(final long licenseID){
        return new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if(licenseID == 0) return builder.conjunction();
                Join<Question, License> j = root.join("license", JoinType.INNER);
                return builder.equal(j.get("id"), licenseID);
            }
        };
    }
 
    public static Specification<Question> equalSubjectID(final long SubjectID){
        return new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if(SubjectID == 0) return builder.conjunction();
                Join<Question, Subject> j = root.join("subject", JoinType.INNER);
                return builder.equal(j.get("id"), SubjectID);
            }
        };
    }
}
