package org.apache.fineract.infrastructure.bse.data;

public class BSEDematData {

    private final Long clientId;
    private final Integer depository;
    private final String depositoryId;
    private final String clientIdd;
    private final String panNo;
    private final String bankName;
    private final String location;
    private final String upiId;
    private final String ifscCode;
    private final Integer category;

    public BSEDematData (final Long clientId, final Integer depository, final String depositoryId, final String clientIdd,
                         final String panNo, final String bankName, final String location, final String upiId, final String ifscCode,
                         final Integer category) {
        this.clientId = clientId;
        this.depository = depository;
        this.depositoryId = depositoryId;
        this.clientIdd = clientIdd;
        this.panNo = panNo;
        this.bankName = bankName;
        this.location = location;
        this.upiId = upiId;
        this.ifscCode = ifscCode;
        this.category = category;
    }

    public Long getClientId() { return this.clientId; }

    public String getDepositoryId() { return this.depositoryId; }

    public String getUpiId() { return this.upiId; }

    public String getPanNo() { return this.panNo; }

    public String getLocation() { return this.location; }

    public String getBankName() { return this.bankName; }

    public String getIfscCode() { return this.ifscCode; }

    public String getClientIdd() { return this.clientIdd; }

    public Integer getCategory() { return this.category; }

    public Integer getDepository() { return this.depository; }

}
