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

import javax.persistence.*;

@Entity
@Table(name = "m_bid_response")
public class ExchangeBidResponse {

    @Column(name = "bidId", unique = true, nullable = false, length = 16)
    private String bidId;

    @Column(name = "Quantity", nullable = false, length = 11)
    private String quantity;

    @Column(name = "Rate", nullable = false, length = 7)
    private String rate;

    @Column(name = "OneOrZero_cd_Cut", length = 1)
    private char cutOff;

    @Column(name = "OrderNo", length = 16)
    private String orderNo;

    @ManyToOne
//    @JoinTable(name = "m_response", joinColumns = @JoinColumn(name = "id"))
    private ExchangeIQResponse exchangeIQResponse;

    public ExchangeBidResponse() {

    }

    public ExchangeBidResponse(String quantity, String rate, String bidId, String orderNo, char cutOff) {
        this.bidId = bidId;
        this.quantity = quantity;
        this.rate = rate;
        this.cutOff = cutOff;
        this.orderNo = orderNo;
//        this.exchangeIQResponse = exchangeIQResponse;
    }

    public String getQuantity() { return this.quantity; }

    public String getRate() { return this.rate; }

    public char getCutOff() { return this.cutOff; }

    public String getBidId() { return this.bidId; }

    public String getOrderNo() { return this.orderNo; }

//    public ExchangeIQResponse getResponseId() { return this.exchangeIQResponse; }

}
