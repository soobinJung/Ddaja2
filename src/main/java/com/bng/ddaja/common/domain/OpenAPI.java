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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
@Table(name = "TB_OPEN_API")
@NoArgsConstructor
@Entity
public class OpenAPI extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OA_ID")
    private long id;

    @Column(name = "CNT_RECPT")
    private long cntRecpt;

    @Column(name = "CNT_PASS")
    private long cntPass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "R_ID")
    private Round round;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "L_ID")
    private License license;

    public void setRound(Round round) {
        if(this.round != null) {
            this.round.getOpenAPIs().remove(this);
        }
        this.round = round;
        if(!round.getOpenAPIs().contains(this)) {
            round.setOpenAPI(this);
        }
    }

    public void setLicense(License license) {
        if(this.license != null) {
            this.license.getOpenAPIs().remove(this);
        }
        this.license = license;
        if(!license.getOpenAPIs().contains(this)) {
            license.setOpenAPI(this);
        }
    }
}