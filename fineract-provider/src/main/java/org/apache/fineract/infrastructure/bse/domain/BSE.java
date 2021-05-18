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

import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.fineract.infrastructure.core.api.JsonCommand;
import org.apache.fineract.infrastructure.core.domain.AbstractPersistableCustom;

@Entity
@Table(name = "m_bse_data")
public class BSE extends AbstractPersistableCustom {

    private String bseName;
    private long bseId;

    public BSE(String bseName, long bseId) {
        this.bseName = bseName;
        this.bseId = bseId;
    }

    public BSE() {

    }

    public static BSE fromJson(final JsonCommand command) {

        final String bseName = command.stringValueOfParameterNamed("name");
        final long bseId = Long.parseLong(command.stringValueOfParameterNamed("id"));

        return new BSE(bseName, bseId);

    }

    public String getBseName() {
        return this.bseName;
    }

    public void setBseName(String bseName) {
        this.bseName = bseName;
    }

    public long getBseId() {
        return this.bseId;
    }

    public void setBseId(long bseId) {
        this.bseId = bseId;
    }
}
