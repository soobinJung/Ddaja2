package com.bng.ddaja.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor
@Entity
public class LicenseInfo extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LI_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "L_ID")
    private License license;

    public void setLicense(License license) {
        if(this.license != null) {
            this.license.getLicenseInfos().remove(this);
        }
        this.license = license;
        if(!license.getLicenseInfos().contains(this)) {
            license.setLicenseInfo(this);
        }
    }
}
