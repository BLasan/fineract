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
package org.apache.fineract.infrastructure.exchange.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Collection;
import javax.transaction.Transactional;
import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResultBuilder;
import org.apache.fineract.infrastructure.core.serialization.FromJsonHelper;
import org.apache.fineract.infrastructure.exchange.data.ExchangeConfigurationData;
import org.apache.fineract.infrastructure.exchange.data.ExchangeLoginResponseData;
import org.apache.fineract.infrastructure.exchange.domain.ExchangeIQRequest;
import org.apache.fineract.infrastructure.exchange.domain.ExchangeIQResponse;
import org.apache.fineract.infrastructure.exchange.exception.ExchangeConfigurationNotFoundException;
import org.apache.fineract.infrastructure.exchange.exception.ExchangeDataNotFoundException;
import org.apache.fineract.infrastructure.exchange.exception.ExchangeIPORequestException;
import org.apache.fineract.infrastructure.exchange.exception.ExchangeUserLoginException;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeDataWritePlatformServiceImpl implements ExchangeDataWritePlatformService {

    private final PlatformSecurityContext context;
    private final ExchangeConfigurationDataReadPlatformService exchangeConfigurationDataReadPlatformService;
    private final ExchangeIQDataReadPlatformService exchangeIQDataReadPlatformService;
    private final FromJsonHelper fromJsonHelper;
    private JsonElement jsonElement;
    private final HttpClient httpClient;
    private static final ObjectMapper objectMapper;
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeDataWritePlatformServiceImpl.class);

    @Autowired
    public ExchangeDataWritePlatformServiceImpl(final PlatformSecurityContext context,
            final ExchangeConfigurationDataReadPlatformService exchangeConfigurationDataReadPlatformService,
            final ExchangeIQDataReadPlatformService exchangeIQDataReadPlatformService, final FromJsonHelper fromJsonHelper,
            final ObjectMapper objectMapper) {
        this.context = context;
        this.exchangeConfigurationDataReadPlatformService = exchangeConfigurationDataReadPlatformService;
        this.exchangeIQDataReadPlatformService = exchangeIQDataReadPlatformService;
        this.fromJsonHelper = fromJsonHelper;
        this.httpClient = HttpClient.newHttpClient();
    }

    static {
        objectMapper = new ObjectMapper();
    }

    @Override
    @Transactional
    public CommandProcessingResult getBSEIPOData(JsonCommand command) {
        return CommandProcessingResult.empty();
    }

    @Override
    @Transactional
    public CommandProcessingResult saveNSEData(Long groupId, JsonCommand command) {
        return CommandProcessingResult.empty();
    }

    @Override
    @Transactional
    public CommandProcessingResult saveBSEData(Long groupId, JsonCommand command) {
        ExchangeConfigurationData exchangeConfigurationData = this.exchangeConfigurationDataReadPlatformService.getBSEConfigurationData();
        if (exchangeConfigurationData == null) {
            throw new ExchangeConfigurationNotFoundException(Long.valueOf(1));
        }

        LOG.info(exchangeConfigurationData.getBaseAPIURL());

        LOG.info("-------------------------------CONFIG-------------------------------------");

        Collection<ExchangeIQRequest> exchangeIQRequest = this.exchangeIQDataReadPlatformService.getBSEIQRequestData(command.getGroupId());
        if (exchangeIQRequest.isEmpty()) {
            throw new ExchangeDataNotFoundException(command.getGroupId());
        }

        String memberId = exchangeConfigurationData.getMemberId().toString();
        String baseAPIURL = exchangeConfigurationData.getBaseAPIURL();
        String password = exchangeConfigurationData.getPassword();
        String userName = exchangeConfigurationData.getUserName();
        String tokenAPI = exchangeConfigurationData.getTokenAPI();
        String applyIPOAPI = exchangeConfigurationData.getApplyIPOAPI();
        String logoutAPI = exchangeConfigurationData.getLogoutAPI();
        String env = exchangeConfigurationData.getEnv();
        String loginId = "";

        String requestBody = String.format("{'memberCode': %s, 'loginid': %s, 'password': %s, 'ibbsid': %s}", memberId, loginId, password,
                userName);

        JsonObject jsonObject;

        try {
            // URL url = new URL(baseAPIURL);
            // HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // con.setRequestProperty("Accept", "application/json");
            // con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // con.setConnectTimeout(5000);

            ExchangeLoginResponseData loginResponseData = (userAuthenticate(baseAPIURL, requestBody, tokenAPI));

            // jsonElement = JsonParser.parseString(userAuthenticate(baseAPIURL, con, requestBody));
            // String memberCode = this.fromJsonHelper.extractStringNamed("membercode", jsonElement);
            // String token = this.fromJsonHelper.extractStringNamed("token", jsonElement);
            // String errorCode = this.fromJsonHelper.extractStringNamed("errorcode", jsonElement);
            // String message = this.fromJsonHelper.extractStringNamed("message", jsonElement);

            String errorCode = loginResponseData.getErrorCode();
            String errorMessage = loginResponseData.getMessage();
            String memberCode = loginResponseData.getMemberCode();

            if (!errorCode.equals("0")) {
                throw new ExchangeUserLoginException(errorCode, memberCode, errorMessage);
            }

            // String ipoRequestBody = String.format("{'scriptId': %s, 'applicationno': %s, 'category': %s,
            // 'applicantname': %s"
            // + "'depository': %s, 'dpid': %s, 'clientbenfid': %s, 'chequereceivedflag': %s, 'chequeamount': %s,
            // 'panno': %s"
            // + "'bankname': %s, 'location': %s, 'accountnumber_upiid': %s, 'ifsccode': %s, 'referenceno': %s,
            // 'asba_upiid': %s}", 1);

            ExchangeIQResponse exchangeIQResponse = ipoOrder(baseAPIURL, requestBody, tokenAPI);

            errorCode = exchangeIQResponse.getErrorCode();
            errorMessage = exchangeIQResponse.getErrorMessage();
            String applicationNo = exchangeIQResponse.getApplicationNo();
            String applicantName = exchangeIQResponse.getApplicationName();

            if (exchangeIQResponse.getErrorCode() != null) {
                throw new ExchangeIPORequestException(errorCode, errorMessage, applicationNo, applicantName);
            }

        } catch (IOException ioException) {
            LOG.error(ioException.getMessage());
        }
        return new CommandProcessingResultBuilder().withCommandId(command.commandId())
                .withEntityId(Long.parseLong(exchangeConfigurationData.getMemberId().toString())).build();
        // return CommandProcessingResult.empty();
    }

    public ExchangeLoginResponseData userAuthenticate(String baseAPIURL, String requestBody, String tokenAPI) throws IOException {
        // connection.setDoInput(true);
        // connection.setDoOutput(true);
        // connection.setRequestMethod("POST");

        // OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
        // writer.write(requestBody);
        // writer.close();
        //
        // BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // StringBuffer jsonString = new StringBuffer();
        // String line;
        // while ((line = br.readLine()) != null) {
        // jsonString.append(line);
        // }
        //
        // br.close();
        // connection.disconnect();

        // var request = HttpRequest.newBuilder(
        // URI.create(baseAPIURL + "/" +tokenAPI))
        // .header("accept", "application/json")
        // .build();

        StringEntity requestEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPost request = new HttpPost(baseAPIURL + "/" + tokenAPI);
            request.setEntity(requestEntity);

            ExchangeLoginResponseData response = client.execute(request,
                    httpResponse -> objectMapper.readValue(httpResponse.getEntity().getContent(), ExchangeLoginResponseData.class));

            System.out.println(response.getMessage());

            return response;
        }

    }

    public ExchangeIQResponse ipoOrder(String baseAPIURL, String requestBody, String tokenAPI) throws IOException {
        StringEntity requestEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPost request = new HttpPost(baseAPIURL + "/" + tokenAPI);
            request.setEntity(requestEntity);

            ExchangeIQResponse response = client.execute(request,
                    httpResponse -> objectMapper.readValue(httpResponse.getEntity().getContent(), ExchangeIQResponse.class));

            System.out.println(response.getChequeAmount());

            return response;
        }
    }

    // public ExchangeIPORequestData getIPOData() {
    // return new ExchangeIPORequestData();
    // }

    // public ExchangeBidData getBidData() {
    // return new ExchangeBidData();
    // }

}
