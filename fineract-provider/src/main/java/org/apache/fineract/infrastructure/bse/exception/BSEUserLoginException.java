package org.apache.fineract.infrastructure.bse.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformException;

public class BSEUserLoginException extends AbstractPlatformException {

    public BSEUserLoginException (String errorCode, String memberCode, String message) {
        super(errorCode, message + " user " + memberCode);
    }
}
