/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.infrastructure.bse.service;

import javax.transaction.Transactional;

import org.apache.fineract.infrastructure.bse.domain.BSEConfiguration;
import org.apache.fineract.infrastructure.bse.domain.BSEIQRequest;
import org.apache.fineract.infrastructure.bse.exception.BSEConfigurationNotFoundException;
import org.apache.fineract.infrastructure.bse.exception.BSEDataNotFoundException;
import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

@Service
public class BSEDataWritePlatformServiceImpl implements BSEDataWritePlatformService {

    private final PlatformSecurityContext context;
    private final BSEConfigurationDataReadPlatformService bseConfigurationDataReadPlatformService;
    private final BSEIQDataReadPlatformService bseiqDataReadPlatformService;

    @Autowired
    public BSEDataWritePlatformServiceImpl(final PlatformSecurityContext context,
                                           final BSEConfigurationDataReadPlatformService bseConfigurationDataReadPlatformService,
                                           final BSEIQDataReadPlatformService bseiqDataReadPlatformService) {
        this.context = context;
        this.bseConfigurationDataReadPlatformService = bseConfigurationDataReadPlatformService;
        this.bseiqDataReadPlatformService = bseiqDataReadPlatformService;
    }

    @Override
    @Transactional
    public CommandProcessingResult getBSEIPOData(JsonCommand command) {
        return CommandProcessingResult.empty();
    }

    @Override
    @Transactional
    public CommandProcessingResult saveBSEData(Long groupId, JsonCommand command) {
        BSEConfiguration bseConfigurationData = this.bseConfigurationDataReadPlatformService.getBSEConfigurationData();
        if (bseConfigurationData == null) {
            throw new BSEConfigurationNotFoundException(Long.valueOf(1));
        }

        Collection<BSEIQRequest> bseiqRequest = this.bseiqDataReadPlatformService.getBSEIQRequestData(command.getGroupId());
        if (bseiqRequest.isEmpty()) {
            throw new BSEDataNotFoundException(command.getGroupId());
        }

        Integer memberId = bseConfigurationData.getMemberId();
        String baseAPIURL = bseConfigurationData.getBaseAPIURL();
        String password = bseConfigurationData.getPassword();
        String userName = bseConfigurationData.getUserName();

        try{
            URL url = new URL("");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
        } catch (MalformedURLException malformedURLException) {

        } catch (IOException ioException) {

        }
        return CommandProcessingResult.empty();
    }

    public String userAuthenticate() {
        return null;
    }

//    public BSEIPOData getIPOData() {
//        return new BSEIPOData();
//    }

//    public BSEBidData getBidData() {
//        return new BSEBidData();
//    }

}
