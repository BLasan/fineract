package org.apache.fineract.infrastructure.bse.data;

public class BSEBidData {

    private final String bidId;
    private final String quantity;
    private final String rate;
    private final String cutoffFlag;
    private final String orderNo;
    private final String actionCode;

    public BSEBidData (final String bidId, final String quantity, final String rate, final String cutoffFlag,
                       final String orderNo, final String actionCode) {
        this.bidId = bidId;
        this.quantity = quantity;
        this.rate = rate;
        this.cutoffFlag = cutoffFlag;
        this.orderNo = orderNo;
        this.actionCode = actionCode;
    }

    public String getBidId() { return this.bidId; }

    public String getQuantity() { return this.quantity; }

    public String getRate() { return this.rate; }

    public String getCutoffFlag() { return this.cutoffFlag; }

    public String getOrderNo() { return this.orderNo; }

    public String getActionCode() { return this.actionCode; }
}
