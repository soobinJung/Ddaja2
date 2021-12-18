package com.bng.ddaja.licenseIf.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.bng.ddaja.common.domain.License;
import com.bng.ddaja.common.domain.LicenseIf;

import org.springframework.data.jpa.domain.Specification;

public class LicenseIfSpec {
    
    public static Specification<LicenseIf> inUseEqual(final Boolean inUse){
        return new Specification<LicenseIf>(){
            public Predicate toPredicate(Root<LicenseIf> root, CriteriaQuery<?> query, CriteriaBuilder builder){
                if (inUse == null) return builder.conjunction();
                return builder.equal(root.get("inUse"), inUse);
            }
        };
    }

    public static Specification<LicenseIf> equalLicenseID(final long licenseID){
        return new Specification<LicenseIf>(){
            public Predicate toPredicate(Root<LicenseIf> root, CriteriaQuery<?> query, CriteriaBuilder builder ){
                if(licenseID == 0) return builder.conjunction();
                Join<LicenseIf, License> j = root.join("license", JoinType.INNER);
                return builder.equal(j.get("id"), licenseID);
            }
        };
    }
}