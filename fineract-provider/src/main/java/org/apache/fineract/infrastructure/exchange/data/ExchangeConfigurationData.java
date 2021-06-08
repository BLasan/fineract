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

    private final String userName;
    private final String password;
    private final String baseAPIURL;
    private final String applyIPOAPI;
    private final String logoutAPI;
    private final String tokenAPI;
    private final String env;
    private final String subscriptionKey;
    private final String subscriptionId;
    private final String version;

    public ExchangeConfigurationData(final String baseAPIURL, final String password, final String userName, final String applyIPOAPI,
            final String logoutAPI, final String tokenAPI, final String env, final String subscriptionId, final String subscriptionKey,
            final String version) {
        this.password = password;
        this.baseAPIURL = baseAPIURL;
        this.userName = userName;
        this.applyIPOAPI = applyIPOAPI;
        this.logoutAPI = logoutAPI;
        this.tokenAPI = tokenAPI;
        this.env = env;
        this.subscriptionId = subscriptionId;
        this.subscriptionKey = subscriptionKey;
        this.version = version;
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

    public String getSubscriptionKey() {
        return this.subscriptionKey;
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public String getVersion() {
        return this.version;
    }

}
