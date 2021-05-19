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

import org.apache.fineract.infrastructure.bse.data.BSEIPOData;
import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class BSEDataWritePlatformServiceImpl implements BSEDataWritePlatformService {

    private final PlatformSecurityContext context;
    private final BSEIPODataReadPlatformService BSEIPODataReadPlatformService;

    @Autowired
    public BSEDataWritePlatformServiceImpl(final PlatformSecurityContext context,
                                           final BSEIPODataReadPlatformService BSEIPODataReadPlatformService) {
        this.context = context;
        this.BSEIPODataReadPlatformService = BSEIPODataReadPlatformService;
    }

    @Override
    @Transactional
    public CommandProcessingResult getBSEIPOData(JsonCommand command) {
        return CommandProcessingResult.empty();
    }

    @Override
    @Transactional
    public CommandProcessingResult saveBSEData(Long groupId, JsonCommand command) {
        BSEIPOData bseipoData = this.BSEIPODataReadPlatformService.getBSEIPOData();
//        String memberCode = bseipoData.getMemberCode();
//        String loginId = bseipoData.getLoginId();
//        String password = bseipoData.getPassword();
//        String ibbsid = bseipoData.getIbbsId();

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
