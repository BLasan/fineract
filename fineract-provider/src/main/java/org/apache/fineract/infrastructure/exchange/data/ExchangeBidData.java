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

public class ExchangeBidData {

    private final String bidId;
    private final Integer quantity;
    private final Double rate;
    private final Integer cutoffFlag;
    private final String orderNo;
    private final Long loanId;

    public ExchangeBidData(final String bidId, final Integer quantity, final Double rate, final Integer cutoffFlag, final String orderNo,
            final Long loanId) {
        this.bidId = bidId;
        this.quantity = quantity;
        this.rate = rate;
        this.cutoffFlag = cutoffFlag;
        this.orderNo = orderNo;
        this.loanId = loanId;
    }

    public String getBidId() {
        return this.bidId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Double getRate() {
        return this.rate;
    }

    public Integer getCutoffFlag() {
        return this.cutoffFlag;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public Long getLoanId() {
        return this.loanId;
    }
}
