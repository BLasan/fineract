package org.apache.fineract.infrastructure.exchange.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class ExchangeConfigurationNotFoundException extends AbstractPlatformResourceNotFoundException {

    public ExchangeConfigurationNotFoundException(Long configId) {
        super("error.msg.bse.configuration.identifier.not.found", "Configuration with identifier `" + configId + "` does not exist", configId);
    }
}
