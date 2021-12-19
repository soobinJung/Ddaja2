package com.bng.ddaja.successComment.spec;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.domain.SuccessComment;

import org.springframework.data.jpa.domain.Specification;


public class SuccessCommentSpec {
    
    public static Specification<SuccessComment> inUseEqual(final Boolean inUse) {
        return new Specification<SuccessComment>() {
            @Override
            public  Predicate toPredicate(Root<SuccessComment> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (inUse == null) return builder.conjunction();
                return builder.equal(root.get("inUse"), inUse);
            }
        };
    }

    public static Specification<SuccessComment> equalLicenseID(final long licenseID){
        return new Specification<SuccessComment>(){
            @Override
            public Predicate toPredicate(Root<SuccessComment> root, CriteriaQuery<?> query, CriteriaBuilder builder ){
                if(licenseID == 0) return builder.conjunction();
                Join<SuccessComment, License> j = root.join("license", JoinType.INNER);
                return builder.equal(j.get("id"), licenseID);
            }
        };
    }
}
 