package org.apache.fineract.infrastructure.bse.service;

import org.apache.fineract.infrastructure.bse.data.BSEConfigurationData;
import org.apache.fineract.infrastructure.bse.domain.BSEConfiguration;

public interface BSEConfigurationDataReadPlatformService {

    BSEConfiguration getBSEConfigurationData();
}
