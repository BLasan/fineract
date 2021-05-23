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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.fineract.infrastructure.bse.domain.BSEConfiguration;
import org.apache.fineract.infrastructure.bse.domain.BSEIQRequest;
import org.apache.fineract.infrastructure.bse.exception.BSEConfigurationNotFoundException;
import org.apache.fineract.infrastructure.bse.exception.BSEDataNotFoundException;
import org.apache.fineract.infrastructure.bse.exception.BSEUserLoginException;
import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.infrastructure.core.serialization.FromJsonHelper;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

@Service
public class BSEDataWritePlatformServiceImpl implements BSEDataWritePlatformService {

    private final PlatformSecurityContext context;
    private final BSEConfigurationDataReadPlatformService bseConfigurationDataReadPlatformService;
    private final BSEIQDataReadPlatformService bseiqDataReadPlatformService;
    private final FromJsonHelper fromJsonHelper;
    private JsonElement jsonElement;

    @Autowired
    public BSEDataWritePlatformServiceImpl(final PlatformSecurityContext context,
                                           final BSEConfigurationDataReadPlatformService bseConfigurationDataReadPlatformService,
                                           final BSEIQDataReadPlatformService bseiqDataReadPlatformService,
                                           final FromJsonHelper fromJsonHelper) {
        this.context = context;
        this.bseConfigurationDataReadPlatformService = bseConfigurationDataReadPlatformService;
        this.bseiqDataReadPlatformService = bseiqDataReadPlatformService;
        this.fromJsonHelper = fromJsonHelper;
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

        String memberId = bseConfigurationData.getMemberId();
        String baseAPIURL = bseConfigurationData.getBaseAPIURL();
        String password = bseConfigurationData.getPassword();
        String userName = bseConfigurationData.getUserName();
        String loginIn = bseConfigurationData.getLoginId();

        String requestBody = String.format("{'memberCode': %s, 'loginid': %s, 'password': %s, 'ibbsid': %s}", memberId, loginIn, password, userName);

        JsonObject jsonObject;

        try{
            URL url = new URL(baseAPIURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setConnectTimeout(5000);
            jsonElement = JsonParser.parseString(userAuthenticate(baseAPIURL, con, requestBody));
            String memberCode =  this.fromJsonHelper.extractStringNamed("membercode", jsonElement);
            String token = this.fromJsonHelper.extractStringNamed("token", jsonElement);
            String errorCode = this.fromJsonHelper.extractStringNamed("errorcode", jsonElement);
            String message = this.fromJsonHelper.extractStringNamed("message", jsonElement);

            if (!errorCode.equals("0")) {
                throw new BSEUserLoginException(errorCode, memberCode, message);
            }

        } catch (MalformedURLException malformedURLException) {

        } catch (IOException ioException) {

        }
        return CommandProcessingResult.empty();
    }

    public String userAuthenticate(String baseAPIURL, HttpURLConnection connection, String requestBody) throws IOException, MalformedURLException {
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        String loginPath = baseAPIURL + "/Login";
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        writer.write(requestBody);
        writer.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer jsonString = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            jsonString.append(line);
        }

        br.close();
        connection.disconnect();

        return jsonString.toString();
    }



//    public BSEIPOData getIPOData() {
//        return new BSEIPOData();
//    }

//    public BSEBidData getBidData() {
//        return new BSEBidData();
//    }

}
