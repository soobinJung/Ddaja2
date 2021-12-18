package com.bng.ddaja.common.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
public class CommonDTO {
    public long id;
    public Date createdDate;
    public Date modifiedDate;
    public boolean isCreated;
}
