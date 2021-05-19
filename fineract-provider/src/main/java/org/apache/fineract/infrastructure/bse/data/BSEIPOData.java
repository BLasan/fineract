package org.apache.fineract.infrastructure.bse.data;

public class BSEIPOData {

    private final String scriptId;
    private final String applicationNo;
    private final String category;
    private final String applicantName;
    private final String depository;
    private final String depositoryId;
    private final String clientId;
    private final char checkReceive;
    private final String checkAmount;
    private final String panNo;
    private final String bankName;
    private final String location;
    private final String accountNo;
    private final String ifscCode;
    private final String referenceNo;
    private final String upiId;
    private final BSEBidData[] bseBidData;

    public BSEIPOData (final String scriptId, final String applicationNo, final String category,
                       final String applicantName, final String depository, final String depositoryId,
                       final String clientId, final char checkReceive, final String checkAmount,
                       final String panNo, final String bankName, final String location, final String accountNo,
                       final String ifscCode, final String referenceNo, final String upiId, final BSEBidData[] bseBidData) {
        this.scriptId = scriptId;
        this.applicationNo = applicationNo;
        this.category = category;
        this.applicantName = applicantName;
        this.depository = depository;
        this.depositoryId = depositoryId;
        this.clientId = clientId;
        this.checkReceive = checkReceive;
        this.checkAmount = checkAmount;
        this.panNo = panNo;
        this.bankName = bankName;
        this.location = location;
        this.accountNo = accountNo;
        this.ifscCode = ifscCode;
        this.referenceNo = referenceNo;
        this.upiId = upiId;
        this.bseBidData = bseBidData;
    }

    public String getScriptId() { return this.scriptId; }

    public String getApplicationNo() { return this.applicationNo; }

    public String getCategory() { return this.category; }

    public String getApplicantName() { return this.applicantName; }

    public String getDepository() { return this.depository; }

    public String getDepositoryId() { return this.depositoryId; }

    public String getClientId() { return this.clientId; }

    public String getCheckAmount() { return this.checkAmount; }

    public String getPanNo() { return this.panNo; }

    public String getBankName() { return this.bankName; }

    public String getLocation() { return this.location; }

    public String getAccountNo() { return this.accountNo; }

    public String getIfscCode() { return this.ifscCode; }

    public String getReferenceNo() { return this.referenceNo; }

    public String getUpiId() { return this.upiId; }

    public char getCheckReceive() { return this.checkReceive; }

}
