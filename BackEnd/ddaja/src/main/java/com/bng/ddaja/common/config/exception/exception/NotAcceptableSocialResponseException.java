package com.bng.ddaja.common.config.exception.exception;

import com.bng.ddaja.common.config.exception.enums.ExceptionCode;

public class NotAcceptableSocialResponseException extends CommonException {
    public NotAcceptableSocialResponseException() {
        super(ExceptionCode.NOT_ACCEPTABLE_SOCIAL_RESPONSE);
    }
    public NotAcceptableSocialResponseException(String message) {
        super(ExceptionCode.NOT_ACCEPTABLE_SOCIAL_RESPONSE, message);
    }
}
