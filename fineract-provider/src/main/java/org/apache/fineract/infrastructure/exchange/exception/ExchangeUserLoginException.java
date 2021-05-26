package org.apache.fineract.infrastructure.exchange.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformException;

public class ExchangeUserLoginException extends AbstractPlatformException {

    public ExchangeUserLoginException(String errorCode, String memberCode, String message) {
        super(errorCode, message + " user " + memberCode);
    }
}
