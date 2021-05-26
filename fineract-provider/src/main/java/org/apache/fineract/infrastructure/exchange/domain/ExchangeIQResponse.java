package org.apache.fineract.infrastructure.exchange.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_response")
public class ExchangeIQResponse {

    @Column(name = "scriptId", length = 10, nullable = false)
    private String scriptId;

    @Column(name = "applicationNo", length = 16, nullable = false)
    private String applicationNo;

    @Column(name = "category", length = 5, nullable = false)
    private String category;

    @Column(name = "applicationName", length = 50, nullable = false)
    private String applicationName;

    @Column(name = "depository", length = 4, nullable = false)
    private String depository;

    @Column(name = "dpId", length = 8, nullable = false)
    private String depositoryId;

    @Column(name = "clientbenfId", length = 16, nullable = false)
    private String clientbenId;

    @Column(name = "chequeReceivedFlag", length = 1, nullable = false)
    private String chequeReceivedFlag;

    @Column(name = "chequeAmount", length = 1, nullable = false)
    private String chequeAmount;

    @Column(name = "panNo", length = 10, nullable = false)
    private String panNo;

    @Column(name = "bankName", length = 6, nullable = false)
    private String bankName;

    @Column(name = "location", length = 6, nullable = false)
    private String location;

    @Column(name = "accountNumberUPIID", length = 45, nullable = false)
    private String accountNumberUPIID;

    @Column(name = "ifscCode", length = 11, nullable = false)
    private String ifscCode;

    @Column(name = "referenceNo", length = 16, nullable = false)
    private String referenceNo;

    @Column(name = "asbaUPIID", length = 1, nullable = false)
    private char asbaUPIID;

    @Column(name = "statusCode", length = 2, nullable = false)
    private String statusCode;

    @Column(name = "statusMessage", length = 100, nullable = false)
    private String statusMessage;

    @Column(name = "errorCode", length = 5)
    private String errorCode;

    @Column(name = "errorMessage", length = 100)
    private String errorMessage;

    public ExchangeIQResponse() {

    }

    public ExchangeIQResponse(String scriptId, String applicationNo, String applicationName, String category,
                              String chequeAmount, String chequeReceivedFlag, String depository, String depositoryId,
                              String clientbenId, String panNo, String bankName, String location, String accountNumberUPIID,
                              String ifscCode, String referenceNo, char asbaUPIID, String statusCode, String statusMessage,
                              String errorCode, String errorMessage) {
        this.scriptId = scriptId;
        this.applicationName = applicationName;
        this.applicationNo = applicationNo;
        this.category = category;
        this.chequeAmount = chequeAmount;
        this.chequeReceivedFlag = chequeReceivedFlag;
        this.depository = depository;
        this.depositoryId = depositoryId;
        this.clientbenId = clientbenId;
        this.panNo = panNo;
        this.bankName = bankName;
        this.location = location;
        this.accountNumberUPIID = accountNumberUPIID;
        this.ifscCode = ifscCode;
        this.referenceNo = referenceNo;
        this.asbaUPIID = asbaUPIID;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getScriptId() { return this.scriptId; }

    public String getApplicationNo() { return this.applicationNo; }

    public String getCategory() { return this.category; }

    public String getLocation() { return this.location; }

    public String getPanNo() { return this.panNo; }

    public String getBankName() { return this.panNo; }

    public String getReferenceNo() { return this.referenceNo; }

    public String getIfscCode() { return this.ifscCode; }

    public String getDepository() { return this.depository; }

    public String getDepositoryId() { return this.depositoryId; }

    public String getApplicationName() { return this.applicationName; }

    public String getClientbenId() { return this.clientbenId; }

    public String getChequeReceivedFlag() { return this.chequeReceivedFlag; }

    public String getChequeAmount() { return this.chequeAmount; }

    public String getAccountNumberUPIID() { return this.accountNumberUPIID; }

    public String getStatusCode() { return this.statusCode; }

    public String getStatusMessage() { return this.statusMessage; }

    public String getErrorCode() { return this.errorCode; }

    public String  getErrorMessage() { return this.errorMessage; }

    public char getAsbaUPIID() { return this.asbaUPIID; }

}
