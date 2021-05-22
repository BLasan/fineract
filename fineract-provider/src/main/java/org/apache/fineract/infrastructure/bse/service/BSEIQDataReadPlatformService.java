package org.apache.fineract.infrastructure.bse.service;

import org.apache.fineract.infrastructure.bse.domain.BSEIQRequest;

import java.util.Collection;

public interface BSEIQDataReadPlatformService {

    Collection<BSEIQRequest> getBSEIQRequestData(Long groupId);
}
