package org.apache.fineract.infrastructure.exchange.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformException;
import org.apache.fineract.infrastructure.core.exception.PlatformInternalServerException;

public class ExchangeIPORequestException extends PlatformInternalServerException {

    public ExchangeIPORequestException (String errorCode, String errorMessage, String applicationNo, String applicantName) {
        super(errorCode, errorMessage, ".application.no.", applicationNo, ".application.name", applicantName);
    }
}
