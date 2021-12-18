package com.bng.ddaja.common.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bng.ddaja.common.enums.AdminRole; 

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false, of = "id")
@Table(name = "TB_ADMIN")
@NoArgsConstructor
@Entity
public class Admin extends CommonEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="A_ID")
    private long id;
    
    @Column(name="ID")
    private String adminId;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="IN_USE")
    private String inUse;

    @Enumerated(EnumType.STRING)
    private AdminRole role;

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List<Notice> notices;

    public void setNotice(Notice notice) {
        this.getNotices().add(notice);
        if(notice.getAdmin() != this) {
            notice.setAdmin(this);
        }
    }
}
