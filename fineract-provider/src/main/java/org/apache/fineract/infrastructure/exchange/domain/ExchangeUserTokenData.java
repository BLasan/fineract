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

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.fineract.infrastructure.core.domain.AbstractPersistableCustom;

@Entity
@Table(name = "m_exchange_user_token")
public class ExchangeUserTokenData extends AbstractPersistableCustom {

    @Column(name = "username", length = 128)
    private String userName;

    @Column(name = "token", length = 512)
    private String token;

    @Column(name = "tokenType", length = 128)
    private String tokenType;

    @Column(name = "expiresIn")
    private Date expiresIn;

    @Column(name = "issued", length = 128)
    private String issued;

    @Column(name = "expiryDate")
    private Date expiryDate;

    public ExchangeUserTokenData() {

    }

    public ExchangeUserTokenData(String userName, String token, String tokenType, Date expiresIn, String issued, Date expiryDate) {
        this.userName = userName;
        this.token = token;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.issued = issued;
        this.expiryDate = expiryDate;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getIssued() {
        return this.issued;
    }

    public String getToken() {
        return this.token;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public Date getExpiresIn() {
        return this.expiresIn;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

}
