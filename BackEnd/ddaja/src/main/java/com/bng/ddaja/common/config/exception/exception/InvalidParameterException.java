package com.bng.ddaja.common.config.exception.exception;

import com.bng.ddaja.common.config.exception.enums.ExceptionCode;

public class InvalidParameterException extends CommonException {

    private static final long serialVersionUID = -214124124141124124L;

	public InvalidParameterException() {
		super(ExceptionCode.INVALID_PARAMETER);
	}
    
}
