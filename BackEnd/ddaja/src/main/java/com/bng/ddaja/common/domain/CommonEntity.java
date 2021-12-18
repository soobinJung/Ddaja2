package com.bng.ddaja.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.ToString;


@ToString
@Getter
@MappedSuperclass
public class CommonEntity {
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false, insertable = false)
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    public Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    public Date modifiedDate;
}
