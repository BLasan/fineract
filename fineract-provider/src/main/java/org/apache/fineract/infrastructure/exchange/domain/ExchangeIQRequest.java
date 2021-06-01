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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.fineract.portfolio.loanaccount.domain.Loan;

@Entity
@Table(name = "m_request")
public class ExchangeIQRequest {

    @Column(name = "reqId", length = 20, unique = true, nullable = false)
    private Long reqId;

    @Column(name = "exchangeId", length = 50, nullable = false)
    private Long exchangeId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @Column(name = "request", length = 255, nullable = false)
    private byte[] request;

    public ExchangeIQRequest(Long reqId, Long exchangeId, Loan loan, byte[] request) {
        this.reqId = reqId;
        this.exchangeId = exchangeId;
        this.loan = loan;
        this.request = request;
    }

    public ExchangeIQRequest() {

    }

    public Long getLoanId() {
        return this.loan.getId();
    }

    public Long getReqId() {
        return this.reqId;
    }

    public Long getExchangeId() {
        return this.exchangeId;
    }

    public byte[] getRequest() {
        return request;
    }

}
