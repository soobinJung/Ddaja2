package com.bng.ddaja.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false, of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TB_LICENSE_IF")
@Entity
public class LicenseIf extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIF_ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "L_ID")
    private License license;

    @Column(name = "IN_USE")
    private boolean inUse;

    @Column(name = "L_IF")
    private String licenseIf;

    public void setLicense( License license ){
        if(this.license != null){
            this.license.getLicenseIfs().remove(this);
        }
        this.license = license;

        if(!license.getLicenseIfs().contains(this)) {
            license.setLicenseIfs(this);
        }
    }
}
