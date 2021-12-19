package com.bng.ddaja.licenses.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.domain.Subject;

import org.springframework.data.jpa.domain.Specification;

public class LicenseSpec {

    public static Specification<License> subjectNameLike(final String subjectName) {
        return new Specification<License>() {
            public Predicate toPredicate(Root<License> root, CriteriaQuery<?> query,
            CriteriaBuilder builder) {
                if (subjectName == null || "".equals(subjectName)) return builder.conjunction();
                Join<License, Subject> j = root.join("subjects", JoinType.INNER);
                return builder.like(j.<String>get("name"), new StringBuilder().append("%").append(subjectName).append("%").toString());
            }
        };
    }

    public static Specification<License> nameLike(final String name) {
        return new Specification<License>() {
            public Predicate toPredicate(Root<License> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (name == null || "".equals(name)) return builder.conjunction();
                return builder.like(root.get("name"), new StringBuilder().append("%").append(name).append("%").toString());
            }
        };
    }

    public static Specification<License> inUseEqual(final Boolean inUse) {
        return new Specification<License>() {
            public  Predicate toPredicate(Root<License> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (inUse == null) return builder.conjunction();
                return builder.equal(root.get("inUse"), inUse);
            }
        };
    }
}
