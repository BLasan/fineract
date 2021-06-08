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

    private final String membercode;
    private final String loginid;
    private final String branchcode;
    private final String token;
    private final String errorcode;
    private final String message;

    public ExchangeLoginResponseData(final String membercode, final String loginid, final String branchcode, final String token,
            final String errorcode, final String message) {
        this.branchcode = branchcode;
        this.loginid = loginid;
        this.membercode = membercode;
        this.errorcode = errorcode;
        this.message = message;
        this.token = token;
    }

    public String getLoginId() {
        return this.loginid;
    }

    public String getMemberCode() {
        return this.membercode;
    }

    public String getToken() {
        return this.token;
    }

    public String getErrorCode() {
        return this.errorcode;
    }

    public String getBranchCode() {
        return this.branchcode;
    }

    public String getMessage() {
        return this.message;
    }
}
