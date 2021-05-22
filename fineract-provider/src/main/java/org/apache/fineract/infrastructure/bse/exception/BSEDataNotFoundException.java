package org.apache.fineract.infrastructure.bse.exception;

import org.apache.fineract.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class BSEDataNotFoundException extends AbstractPlatformResourceNotFoundException {

    public BSEDataNotFoundException(Long groupId) {
        super("error.msg.bse.group.data.identifier.not.found", "Group Data with identifier `" + groupId + "` does not exist", groupId);
    }
}
