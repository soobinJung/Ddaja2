package com.bng.ddaja.common.config.exception.exception;

import com.bng.ddaja.common.config.exception.enums.ExceptionCode;

public class NotAcceptableSocialLoginException extends CommonException {
    public NotAcceptableSocialLoginException() {
        super(ExceptionCode.NOT_ACCEPTABLE_SOCIAL_LOGIN);
    }
    public NotAcceptableSocialLoginException(String meesage) {
        super(ExceptionCode.NOT_ACCEPTABLE_SOCIAL_LOGIN, meesage);
    }
}
