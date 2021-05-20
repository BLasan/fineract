package org.apache.fineract.infrastructure.bse.domain;

import javax.persistence.*;

@Entity
@Table(name = "Bid")
public class BSEBid {

    @Column(name = "loan_id", unique = true, nullable = false, length = 20)
    @OneToOne
    @JoinTable(name = "m_loan", joinColumns = @JoinColumn(name = "id"))
    private Long loanId;

    @Column(name = "Quantity", nullable = false, length = 11)
    private Integer quantity;

    @Column(name = "Rate", precision = 6, length = 19)
    private Double rate;

    @Column(name = "OneOrZero_cd_Cut", length = 11)
    private Integer cutOff;

    @Column(name = "Bid Id", length = 16)
    private String bidId;

    @Column(name = "Order No", length = 16)
    private String orderNo;

    public BSEBid() {

    }

    public BSEBid(Long loanId, Integer quantity, Double rate, Integer cutOff, String bidId, String orderNo) {
        this.bidId = bidId;
        this.loanId = loanId;
        this.quantity = quantity;
        this.rate = rate;
        this.cutOff = cutOff;
        this.orderNo = orderNo;
    }

    public Long getLoanId() { return this.loanId; }

    public Integer getQuantity() { return this.quantity; }

    public Double getRate() { return this.rate; }

    public Integer getCutOff() { return this.cutOff; }

    public String getBidId() { return this.bidId; }

    public String getOrderNo() { return this.orderNo; }

}
