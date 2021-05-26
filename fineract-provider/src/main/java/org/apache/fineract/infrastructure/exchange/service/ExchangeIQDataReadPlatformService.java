package org.apache.fineract.infrastructure.exchange.service;

import org.apache.fineract.infrastructure.exchange.domain.ExchangeIQRequest;

import java.util.Collection;

public interface ExchangeIQDataReadPlatformService {

    Collection<ExchangeIQRequest> getBSEIQRequestData(Long groupId);
}
