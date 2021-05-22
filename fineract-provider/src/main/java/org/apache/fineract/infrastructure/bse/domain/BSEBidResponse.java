package org.apache.fineract.infrastructure.bse.domain;

import javax.persistence.*;

@Entity
@Table(name = "m_bid_response")
public class BSEBidResponse {

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

    @Column(name = "responseId", length = 15)
    @ManyToOne
    @JoinTable(name = "m_response", joinColumns = @JoinColumn(name = "id"))
    private Integer responseId;

    public BSEBidResponse() {

    }

    public BSEBidResponse(String quantity, String rate, String bidId, String orderNo, char cutOff, Integer responseId) {
        this.bidId = bidId;
        this.quantity = quantity;
        this.rate = rate;
        this.cutOff = cutOff;
        this.orderNo = orderNo;
        this.responseId = responseId;
    }

    public String getQuantity() { return this.quantity; }

    public String getRate() { return this.rate; }

    public char getCutOff() { return this.cutOff; }

    public String getBidId() { return this.bidId; }

    public String getOrderNo() { return this.orderNo; }

    public Integer getResponseId() { return this.responseId; }

}
