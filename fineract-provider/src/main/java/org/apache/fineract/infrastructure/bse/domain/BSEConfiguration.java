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
package org.apache.fineract.infrastructure.bse.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.domain.AbstractPersistableCustom;

@Entity
@Table(name = "m_bse_configuration")
public class BSEConfiguration extends AbstractPersistableCustom {

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "baseAPIURL")
    private String baseAPIURL;

    @Column(name = "memberId")
    private Integer memberId;

    public BSEConfiguration(String userName, String password, String baseAPIURL, Integer memberId) {
        this.baseAPIURL = baseAPIURL;
        this.userName = userName;
        this.password = password;
        this.memberId = memberId;
    }

    public BSEConfiguration() {

    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() { return this.password; }

    public String getBaseAPIURL() { return this.baseAPIURL; }

    public Integer getMemberId() { return this.memberId; }

}
