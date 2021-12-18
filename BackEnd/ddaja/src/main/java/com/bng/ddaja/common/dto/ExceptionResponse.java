package com.bng.ddaja.common.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.bng.ddaja.common.config.exception.enums.ExceptionCode;
import com.bng.ddaja.common.config.exception.exception.CommonException;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reason;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ExceptionDetail> details;

    public ExceptionResponse (ExceptionCode errorCode) {
        this.code = errorCode.getStatus();
        this.message = errorCode.getMessage();
        details = null;
    }

    public ExceptionResponse (CommonException e) {
        this.code = e.getErrorCode().getStatus();
        this.message =  e.getMessage() == null ? e.getErrorCode().getMessage() : e.getMessage();
    }
    
    public void setDetails(List<FieldError> fieldErrors) {
        details = fieldErrors.stream()
                            .map(e -> new ExceptionDetail(e))
                            .collect(Collectors.toList());
    }
}