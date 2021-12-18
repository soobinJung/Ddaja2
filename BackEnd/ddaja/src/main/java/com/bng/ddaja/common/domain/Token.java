package com.bng.ddaja.common.domain;

import java.util.Date;

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

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "id")
@Table(name = "TB_TOKEN")
@Entity
public class Token extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="T_ID")
    private long id;
    
    @Column(name="ACCESS")
    private String access;
    
    @Column(name="REFRESH")
    private String refresh;

    @Column(name="EXPIRE")
    private Date expire;

    @Column(name="ISSUANCE")
    private String issuance;

    @Column(name="C_ID")
    private String clientID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_ID")
    private User user;

    public void setUser(User user) {
        if(this.user != null) {
            this.user.getTokens().remove(this);
        }
        this.user = user;
        if(!user.getTokens().contains(this)) {
            user.setToken(this);
        }
    }
}
