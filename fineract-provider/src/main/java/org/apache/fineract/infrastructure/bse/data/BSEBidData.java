package org.apache.fineract.infrastructure.bse.data;

public class BSEBidData {

    private final String bidId;
    private final Integer quantity;
    private final Double rate;
    private final Integer cutoffFlag;
    private final String orderNo;
    private final Long loanId;

    public BSEBidData (final String bidId, final Integer quantity, final Double rate, final Integer cutoffFlag,
                       final String orderNo, final Long loanId) {
        this.bidId = bidId;
        this.quantity = quantity;
        this.rate = rate;
        this.cutoffFlag = cutoffFlag;
        this.orderNo = orderNo;
        this.loanId = loanId;
    }

    public String getBidId() { return this.bidId; }

    public Integer getQuantity() { return this.quantity; }

    public Double getRate() { return this.rate; }

    public Integer getCutoffFlag() { return this.cutoffFlag; }

    public String getOrderNo() { return this.orderNo; }

    public Long getLoanId() { return this.loanId; }
}
