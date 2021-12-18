package com.bng.ddaja.round.spec;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.domain.Round;

public class RoundSpec {
    
    public static Specification <Round> examYearLike(final String examYear){
        return new Specification<Round>(){
            public Predicate toPredicate (Root<Round> root, CriteriaQuery<?> query, CriteriaBuilder builder){
                if(examYear == null || "".equals(examYear)) return builder.conjunction();
                return builder.like(root.get("examYear"), new StringBuffer().append("%").append(examYear).append("%").toString());
            }
        };
    }

    public static Specification <Round> roundEqual(final int round){
        return new Specification<Round>(){
            public Predicate toPredicate (Root<Round> root, CriteriaQuery<?> query, CriteriaBuilder builder){
                if(round == 0) return builder.conjunction();
                return builder.equal(root.get("round"), round);
            }
        };
    }

    public static Specification<Round> inUseEqual(final Boolean inUse){
        return new Specification<Round>(){
            public Predicate toPredicate(Root<Round> root, CriteriaQuery<?> query, CriteriaBuilder builder){
                if (inUse == null) return builder.conjunction();
                return builder.equal(root.get("inUse"), inUse);
            }
        };
    }

    public static Specification<Round> equalLicenseID(final long licenseID){
        return new Specification<Round>(){
            public Predicate toPredicate(Root<Round> root, CriteriaQuery<?> query, CriteriaBuilder builder ){
                if(licenseID == 0) return builder.conjunction();
                Join<Round, License> j = root.join("license", JoinType.INNER);
                return builder.equal(j.get("id"), licenseID);
            }
        };
    }
}