package org.apache.fineract.infrastructure.bse.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class BSEConfigurationNotFoundException extends AbstractPlatformResourceNotFoundException {

    public BSEConfigurationNotFoundException(Long configId) {
        super("error.msg.bse.configuration.identifier.not.found", "Configuration with identifier `" + configId + "` does not exist", configId);
    }
}
