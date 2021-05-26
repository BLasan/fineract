package org.apache.fineract.infrastructure.exchange.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class ExchangeDataNotFoundException extends AbstractPlatformResourceNotFoundException {

    public ExchangeDataNotFoundException(Long groupId) {
        super("error.msg.bse.group.data.identifier.not.found", "Group Data with identifier `" + groupId + "` does not exist", groupId);
    }
}
