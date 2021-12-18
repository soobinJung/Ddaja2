package com.bng.ddaja.common.config.exception.exception;

import com.bng.ddaja.common.config.exception.enums.ExceptionCode;

public class MemberNotFoundException extends CommonException {
    
  private static final long serialVersionUID = -214124124124214124L;

  public MemberNotFoundException() {
		super(ExceptionCode.NOT_FOUND_MEMBER);
	}
  public MemberNotFoundException(String message) {
    super(ExceptionCode.NOT_FOUND_MEMBER, message);
  }
}