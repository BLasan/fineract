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
package org.apache.fineract.infrastructure.exchange.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "m_bse_user_token")
public class ExchangeUserTokenData extends AbstractPersistable {

    @Column(name = "memberId", length = 10, unique = true)
    private String memberId;

    @Column(name = "token", length = 50)
    private String token;

    @Column(name = "tokenType", length = 20)
    private String tokenType;

    @Column(name = "expireDate")
    private Date expires;

    @Column(name = "loginId", length = 15, unique = true)
    private String loginId;

//    @Column(name = "branchCode", length = 10)
//    private final String branchCode;
//
//    @Column(name = "loginId", length = 15)
//    private final String loginId;
//
//    @Column(name = "errorCode", length = 3)
//    private final String errorCode;
//
//    @Column(name = "message", length = 250)
//    private final String message;

    public ExchangeUserTokenData() {

    }

    public ExchangeUserTokenData(String memberId, String token, String tokenType, Date expires, String loginId) {
        this.memberId = memberId;
        this.token = token;
        this.tokenType = tokenType;
        this.expires = expires;
        this.loginId = loginId;
    }

    public String getLoginId() { return this.loginId; }

    public String getMemberId() { return this.memberId; }

    public String getToken() { return this.token; }

    public String getTokenType() { return this.tokenType; }

    public Date getExpires() { return this.expires; }
}
