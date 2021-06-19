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
package org.apache.fineract.portfolio.collateralmanagement.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.apache.fineract.portfolio.collateralmanagement.data.ClientCollateralManagementData;
import org.apache.fineract.portfolio.collateralmanagement.data.LoanTransactionData;
import org.apache.fineract.portfolio.collateralmanagement.domain.ClientCollateralManagement;
import org.apache.fineract.portfolio.collateralmanagement.domain.ClientCollateralManagementRepositoryWrapper;
import org.apache.fineract.portfolio.loanaccount.domain.LoanCollateralManagement;
import org.apache.fineract.portfolio.loanaccount.domain.LoanTransaction;
import org.apache.fineract.portfolio.loanaccount.domain.LoanTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientCollateralManagementReadPlatformServiceImpl implements ClientCollateralManagementReadPlatformService {

    private final PlatformSecurityContext context;
    private final ClientCollateralManagementRepositoryWrapper clientCollateralManagementRepositoryWrapper;
    private final LoanTransactionRepository loanTransactionRepository;

    @Autowired
    public ClientCollateralManagementReadPlatformServiceImpl(final PlatformSecurityContext context,
            final ClientCollateralManagementRepositoryWrapper clientCollateralManagementRepositoryWrapper,
            final LoanTransactionRepository loanTransactionRepository) {
        this.context = context;
        this.clientCollateralManagementRepositoryWrapper = clientCollateralManagementRepositoryWrapper;
        this.loanTransactionRepository = loanTransactionRepository;
    }

    @Override
    public List<ClientCollateralManagement> getCollateralProductsPerClient(final Long clientId) {
        return this.clientCollateralManagementRepositoryWrapper.getCollateralsPerClient(clientId);
    }

    @Override
    public List<ClientCollateralManagementData> getClientCollaterals(final Long clientId) {
        return this.clientCollateralManagementRepositoryWrapper.getClientCollateralData(clientId);
    }

    @Override
    public ClientCollateralManagementData getClientCollateralManagementData(final Long collateralId) {
        final ClientCollateralManagement clientCollateralManagement = this.clientCollateralManagementRepositoryWrapper
                .getCollateral(collateralId);
        BigDecimal basePrice = clientCollateralManagement.getCollaterals().getBasePrice();
        BigDecimal pctToBase = clientCollateralManagement.getCollaterals().getPctToBase().divide(BigDecimal.valueOf(100));
        BigDecimal quantity = clientCollateralManagement.getQuantity();
        BigDecimal total = basePrice.multiply(quantity);
        BigDecimal totalCollateral = total.multiply(pctToBase);
        Set<LoanCollateralManagement> loanCollateralManagementSet = clientCollateralManagement.getLoanCollateralManagementSet();

        // Comparator<LoanCollateralManagement> byTransactionDate = new Comparator<LoanCollateralManagement>() {
        // public int compare(LoanCollateralManagement c1, LoanCollateralManagement c2) {
        // if (c1.getLoanTransaction().getCreatedDateTime().isBefore(c2.getLoanTransaction().getCreatedDateTime()))
        // return 1;
        // else return -1;
        // }
        // };

        List<LoanTransactionData> loanTransactionDataList = new ArrayList<>();
        for (LoanCollateralManagement loanCollateralManagement : loanCollateralManagementSet) {
            Long transactionId = loanCollateralManagement.getLoanTransaction().getId();
            LoanTransaction loanTransaction = this.loanTransactionRepository.findById(transactionId).orElseThrow();
            LoanTransactionData loanTransactionData = LoanTransactionData.instance(loanTransaction.getLoan().getId(),
                    loanTransaction.getCreatedDateTime(), loanTransaction.getOutstandingLoanBalance(),
                    loanTransaction.getPrincipalPortion());
            loanTransactionDataList.add(loanTransactionData);
        }

        // Collections.sort(loanCollateralManagementList, byTransactionDate);

        return ClientCollateralManagementData.instance(clientCollateralManagement.getCollaterals().getName(),
                clientCollateralManagement.getQuantity(), total, totalCollateral, clientCollateralManagement.getClient().getId(),
                loanTransactionDataList);
    }
}