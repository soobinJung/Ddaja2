package com.bng.ddaja.subject.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bng.ddaja.common.domain.Subject;

import org.springframework.data.jpa.domain.Specification;

public class SubjectSpec {

    public static Specification<Subject> nameLike(final String name) {
        return new Specification<Subject>() {
            public Predicate toPredicate(Root<Subject> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (name == null || "".equals(name)) return builder.conjunction();
                return builder.like(root.get("name"), new StringBuilder().append("%").append(name).append("%").toString());
            }
        };
    }

    public static Specification<Subject> inUseEqual(final Boolean inUse){
        return new Specification<Subject>(){
            @Override
            public Predicate toPredicate(Root<Subject> root, CriteriaQuery<?> query, CriteriaBuilder builder){
                if (inUse == null) return builder.conjunction();
                return builder.equal(root.get("inUse"), inUse);
            }
        };
    }
}