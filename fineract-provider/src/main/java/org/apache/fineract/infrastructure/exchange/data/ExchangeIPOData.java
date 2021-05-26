package org.apache.fineract.infrastructure.exchange.data;

public class ExchangeIPOData {

    private final String scriptId;
    private final long groupId;
    private final String applicationNo;
    private final String category;
//    private final String applicantName;
//    private final String depository;
//    private final String depositoryId;
//    private final String clientId;
    private final char checkReceive;
//    private final String checkAmount;
//    private final String panNo;
//    private final String bankName;
//    private final String location;
//    private final String accountNo;
//    private final String ifscCode;
    private final String referenceNo;
    private final String upiId;
//    private final ExchangeBidData[] bseBidData;

    public ExchangeIPOData(final String scriptId, final String applicationNo, final String category,
                           final long groupId, final char checkReceive, final String referenceNo, final String upiId) {
        this.scriptId = scriptId;
        this.applicationNo = applicationNo;
        this.category = category;
        this.checkReceive = checkReceive;
        this.referenceNo = referenceNo;
        this.upiId = upiId;
        this.groupId = groupId;
    }

    public String getScriptId() { return this.scriptId; }

    public String getApplicationNo() { return this.applicationNo; }

    public String getCategory() { return this.category; }

    public long getGroupId() { return this.groupId; }

    public String getReferenceNo() { return this.referenceNo; }

    public String getUpiId() { return this.upiId; }

    public char getCheckReceive() { return this.checkReceive; }

}
