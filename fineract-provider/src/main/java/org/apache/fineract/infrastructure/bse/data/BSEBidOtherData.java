package org.apache.fineract.infrastructure.bse.data;


public class BSEBidOtherData {

    private final String referenceNo;
    private final Integer checkReceive;
    private final Long loanId;
    private final Integer upiId;
    private final Double checkAmount;
    private final Integer category;

    public BSEBidOtherData(final String referenceNo, final Integer checkReceive, final Long loanId, final Integer upiId,
                           final Double checkAmount, final Integer category) {
        this.referenceNo = referenceNo;
        this.checkReceive = checkReceive;
        this.checkAmount = checkAmount;
        this.loanId = loanId;
        this.upiId = upiId;
        this.category = category;
    }

    public String getReferenceNo() { return this.referenceNo; }

    public Integer getCheckReceive() { return this.checkReceive; }

    public Long getLoanId() { return this.loanId; }

    public Integer getUpiId() { return this.upiId; }

    public Double getCheckAmount() { return this.checkAmount; }

    public Integer getCategory() { return this.category; }

}
