package org.apache.fineract.infrastructure.exchange.service;

import org.apache.fineract.infrastructure.exchange.domain.ExchangeConfiguration;

public interface ExchangeConfigurationDataReadPlatformService {

    ExchangeConfiguration getBSEConfigurationData();
}
