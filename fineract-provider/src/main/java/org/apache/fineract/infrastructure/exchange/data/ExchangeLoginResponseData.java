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

public class ExchangeLoginResponseData {

    private final String memberCode;
    private final String loginId;
    private final String branchCode;
    private final String token;
    private final String errorCode;
    private final String message;

    public ExchangeLoginResponseData (final String memberCode, final String loginId, final String branchCode, final String token,
                                      final String errorCode, final String message) {
        this.branchCode = branchCode;
        this.loginId = loginId;
        this.memberCode = memberCode;
        this.errorCode = errorCode;
        this.message = message;
        this.token = token;
    }

    public String getLoginId() { return this.loginId; }

    public String getMemberCode() { return this.memberCode; }

    public String getToken() { return this.token; }

    public String getErrorCode() { return this.errorCode; }

    public String getBranchCode() { return this.branchCode; }

    public String getMessage() { return this.message; }
}
