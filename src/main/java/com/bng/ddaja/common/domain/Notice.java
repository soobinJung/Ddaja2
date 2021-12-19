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
@Table(name = "TB_NOTICE")
@NoArgsConstructor
@Entity
public class Notice extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "N_ID")
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "IN_USE")
    private String inUse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "A_ID")
    private Admin admin;

    public void setAdmin(Admin admin) {
        if(this.admin != null) {
            this.admin.getNotices().remove(this);
        }
        this.admin = admin;
        if(!admin.getNotices().contains(this)) {
            admin.setNotice(this);
        }
    }
}
