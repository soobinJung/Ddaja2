package com.bng.ddaja.words.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bng.ddaja.common.domain.Word;
import com.bng.ddaja.common.domain.License;

import org.springframework.data.jpa.domain.Specification;

public class WordSpec {
    
    public static Specification<Word> nameLike(final String name) {
        return new Specification<Word>() {
            public Predicate toPredicate(Root<Word> root, CriteriaQuery<?> query, CriteriaBuilder builder ) {
                if(name == null || "".equals(name) ) return builder.conjunction();
                return builder.like(root.get("title"), new StringBuilder().append("%").append(name).append("%").toString());
            }
        };
    }

    public static Specification<Word> equalLicenseID(final long licenseID){
        return new Specification<Word>(){
            public Predicate toPredicate(Root<Word> root, CriteriaQuery<?> query, CriteriaBuilder builder ){
                if(licenseID == 0) return builder.conjunction();
                Join<Word, License> j = root.join("license", JoinType.INNER);
                return builder.equal(j.get("id"), licenseID);
            }
        };
    }

    public static Specification<Word> inUseEqual(final Boolean inUse){
        return new Specification<Word>() {
            public  Predicate toPredicate(Root<Word> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (inUse == null) return builder.conjunction();
                return builder.equal(root.get("inUse"), inUse);
            }
        };
    }
}