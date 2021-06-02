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
package org.apache.fineract.infrastructure.exchange.data;

public class ExchangeConfigurationData {

    private final Long memberId;
    private final String userName;
    private final String password;
    private final String baseAPIURL;
    private final String applyIPOAPI;
    private final String logoutAPI;
    private final String tokenAPI;
    private final String env;

    public ExchangeConfigurationData(final Long memberId, final String baseAPIURL, final String password, final String userName,
            final String applyIPOAPI, final String logoutAPI, final String tokenAPI, final String env) {
        this.memberId = memberId;
        this.password = password;
        this.baseAPIURL = baseAPIURL;
        this.userName = userName;
        this.applyIPOAPI = applyIPOAPI;
        this.logoutAPI = logoutAPI;
        this.tokenAPI = tokenAPI;
        this.env = env;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getBaseAPIURL() {
        return this.baseAPIURL;
    }

    public String getApplyIPOAPI() {
        return this.applyIPOAPI;
    }

    public String getLogoutAPI() {
        return this.logoutAPI;
    }

    public String getTokenAPI() {
        return this.tokenAPI;
    }

    public String getEnv() {
        return this.env;
    }

}
