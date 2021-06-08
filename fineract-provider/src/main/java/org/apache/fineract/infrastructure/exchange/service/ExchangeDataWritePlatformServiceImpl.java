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
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Date;
import javax.transaction.Transactional;
import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResult;
import org.apache.fineract.infrastructure.core.data.CommandProcessingResultBuilder;
import org.apache.fineract.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import org.apache.fineract.infrastructure.core.serialization.FromJsonHelper;
import org.apache.fineract.infrastructure.exchange.data.ExchangeConfigurationData;
import org.apache.fineract.infrastructure.exchange.data.ExchangeLoginRequestData;
import org.apache.fineract.infrastructure.exchange.data.ExchangeLoginResponseData;
import org.apache.fineract.infrastructure.exchange.domain.ExchangeIQResponse;
import org.apache.fineract.infrastructure.exchange.domain.ExchangeIQResponseRepositoryWrapper;
import org.apache.fineract.infrastructure.exchange.domain.ExchangeUserTokenData;
import org.apache.fineract.infrastructure.exchange.domain.ExchangeUserTokenRepositoryWrapper;
import org.apache.fineract.infrastructure.exchange.exception.ExchangeConfigurationNotFoundException;
import org.apache.fineract.infrastructure.exchange.exception.ExchangeIPORequestException;
import org.apache.fineract.infrastructure.exchange.exception.ExchangeUserLoginException;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
    private final ExchangeUserTokenRepositoryWrapper exchangeUserTokenRepositoryWrapper;
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeDataWritePlatformServiceImpl.class);
    private final DefaultToApiJsonSerializer<ExchangeLoginRequestData> toApiJsonSerializer;
    private final ExchangeIQResponseRepositoryWrapper exchangeIQResponseRepositoryWrapper;

    @Autowired
    public ExchangeDataWritePlatformServiceImpl(final PlatformSecurityContext context,
            final ExchangeConfigurationDataReadPlatformService exchangeConfigurationDataReadPlatformService,
            final ExchangeIQDataReadPlatformService exchangeIQDataReadPlatformService, final FromJsonHelper fromJsonHelper,
            final ObjectMapper objectMapper, final ExchangeUserTokenRepositoryWrapper exchangeUserTokenRepositoryWrapper,
            final DefaultToApiJsonSerializer<ExchangeLoginRequestData> toApiJsonSerializer,
            final ExchangeIQResponseRepositoryWrapper exchangeIQResponseRepositoryWrapper) {
        this.context = context;
        this.exchangeConfigurationDataReadPlatformService = exchangeConfigurationDataReadPlatformService;
        this.exchangeIQDataReadPlatformService = exchangeIQDataReadPlatformService;
        this.fromJsonHelper = fromJsonHelper;
        this.httpClient = HttpClient.newHttpClient();
        this.exchangeUserTokenRepositoryWrapper = exchangeUserTokenRepositoryWrapper;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.exchangeIQResponseRepositoryWrapper = exchangeIQResponseRepositoryWrapper;
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

        // Collection<ExchangeIQRequest> exchangeIQRequest =
        // this.exchangeIQDataReadPlatformService.getBSEIQRequestData(command.getGroupId());
        // if (exchangeIQRequest.isEmpty()) {
        // throw new ExchangeDataNotFoundException(command.getGroupId());
        // }

        String memberCode = exchangeConfigurationData.getSubscriptionId();
        String baseAPIURL = exchangeConfigurationData.getBaseAPIURL();
        String password = exchangeConfigurationData.getPassword();
        String loginId = exchangeConfigurationData.getUserName();
        String tokenAPI = exchangeConfigurationData.getTokenAPI();
        String applyIPOAPI = exchangeConfigurationData.getApplyIPOAPI();
        String logoutAPI = exchangeConfigurationData.getLogoutAPI();
        String env = exchangeConfigurationData.getEnv();
        String subscriptionKey = exchangeConfigurationData.getSubscriptionKey();
        String subscriptionId = exchangeConfigurationData.getSubscriptionId();
        String version = exchangeConfigurationData.getVersion();

        ExchangeLoginRequestData exchangeLoginRequestData = new ExchangeLoginRequestData(subscriptionId, loginId, subscriptionKey,
                password);
        String requestBody = this.toApiJsonSerializer.serialize(exchangeLoginRequestData).toLowerCase();

        // String requestBody = String.format("{'memberCode': %s, 'loginid': %s, 'password': %s, 'ibbsid': %s}",
        // memberId.toString(), loginId, password,
        // userName);

        try {
            // URL url = new URL(baseAPIURL);
            // HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // con.setRequestProperty("Accept", "application/json");
            // con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // con.setConnectTimeout(5000);

            final boolean isTokenPresent = checkToken(loginId);
            String errorCode = "";
            String errorMessage = "";

            if (isTokenPresent) {
                LOG.info("Token is not expired !!!");
            } else {
                ExchangeLoginResponseData loginResponseData = userAuthenticate(baseAPIURL, requestBody, tokenAPI, version);

                // jsonElement = JsonParser.parseString(userAuthenticate(baseAPIURL, con, requestBody));
                // String memberCode = this.fromJsonHelper.extractStringNamed("membercode", jsonElement);
                // String token = this.fromJsonHelper.extractStringNamed("token", jsonElement);
                // String errorCode = this.fromJsonHelper.extractStringNamed("errorcode", jsonElement);
                // String message = this.fromJsonHelper.extractStringNamed("message", jsonElement);

                errorCode = loginResponseData.getErrorCode();
                errorMessage = loginResponseData.getMessage();
                String responseMemberCode = loginResponseData.getMemberCode();
                String token = loginResponseData.getToken();
                String returnedLoginId = loginResponseData.getLoginId();
                LOG.info(token);

                if (!errorCode.equals("0") || !errorMessage.equals(" ")) {
                    throw new ExchangeUserLoginException(errorCode, responseMemberCode, errorMessage);
                }

                ExchangeUserTokenData exchangeUserTokenData = new ExchangeUserTokenData(loginId, token, " ", new Date(), "bse", new Date());

                updateToken(exchangeUserTokenData);

            }

            /**
             * TODO: Get IPO Request Payload
             */

            // String ipoRequestBody = String.format("{'scriptId': %s, 'applicationno': %s, 'category': %s,
            // 'applicantname': %s"
            // + "'depository': %s, 'dpid': %s, 'clientbenfid': %s, 'chequereceivedflag': %s, 'chequeamount': %s,
            // 'panno': %s"
            // + "'bankname': %s, 'location': %s, 'accountnumber_upiid': %s, 'ifsccode': %s, 'referenceno': %s,
            // 'asba_upiid': %s}", 1);

            ExchangeIQResponse exchangeIQResponse = ipoOrder(baseAPIURL, requestBody, applyIPOAPI, version);

            errorCode = exchangeIQResponse.getErrorCode();
            errorMessage = exchangeIQResponse.getErrorMessage();
            String applicationNo = exchangeIQResponse.getApplicationNo();
            String applicantName = exchangeIQResponse.getApplicationName();

            if (exchangeIQResponse.getErrorCode() != null) {
                throw new ExchangeIPORequestException(errorCode, errorMessage, applicationNo, applicantName);
            }

            saveIQResponse(exchangeIQResponse);

        } catch (IOException ioException) {
            LOG.error(ioException.getMessage());
            throw new ExchangeUserLoginException("500", "1", "Internal Server Error");
        }
        return new CommandProcessingResultBuilder().withCommandId(command.commandId())
                .withClientId(Long.parseLong(exchangeConfigurationData.getUserName())).withGroupId(command.getGroupId()).build();
        // return CommandProcessingResult.empty();
    }

    private ExchangeLoginResponseData userAuthenticate(String baseAPIURL, String requestBody, String tokenAPI, String version)
            throws IOException {
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

            HttpPost request = new HttpPost(baseAPIURL + "/" + version + "/" + tokenAPI.toLowerCase());
            request.setEntity(requestEntity);

            CloseableHttpResponse response1 = client.execute(request);

            String response3 = EntityUtils.toString(response1.getEntity());

            LOG.info(response3);

            ExchangeLoginResponseData response = this.fromJsonHelper.fromJson(response3, ExchangeLoginResponseData.class);

            return response;
        }

    }

    private ExchangeIQResponse ipoOrder(String baseAPIURL, String requestBody, String tokenAPI, String version) throws IOException {
        StringEntity requestEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPost request = new HttpPost(baseAPIURL + "/" + version + "/" + tokenAPI);
            request.setEntity(requestEntity);

            ExchangeIQResponse response = client.execute(request,
                    httpResponse -> objectMapper.readValue(httpResponse.getEntity().getContent(), ExchangeIQResponse.class));

            System.out.println(response.getChequeAmount());

            return response;
        }
    }

    private boolean checkToken(String userName) {
        ExchangeUserTokenData userTokenData = this.exchangeUserTokenRepositoryWrapper.get(userName);
        Date todayDate = new Date();

        if (userTokenData == null) {
            return false;
        }

        if (userTokenData.getToken() == null) {
            return false;
        }

        if (userTokenData.getExpiryDate().before(todayDate)) {
            return false;
        }

        return true;
    }

    private void updateToken(ExchangeUserTokenData exchangeUserTokenData) {
        this.exchangeUserTokenRepositoryWrapper.saveAndFlush(exchangeUserTokenData);
    }

    private void saveIQResponse(final ExchangeIQResponse exchangeIQResponse) {
        this.exchangeIQResponseRepositoryWrapper.save(exchangeIQResponse);
    }

}
