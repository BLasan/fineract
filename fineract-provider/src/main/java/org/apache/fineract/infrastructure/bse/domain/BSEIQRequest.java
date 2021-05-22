package org.apache.fineract.infrastructure.bse.domain;

import javax.persistence.*;

@Entity
@Table(name = "m_request")
public class BSEIQRequest {

    @Column(name = "id", length = 20, unique = true, nullable = false)
    private Long reqId;

    @Column(name = "exchangeId", length = 50, nullable = false)
    private Long exchangeId;

    @ManyToOne
    @JoinTable(name = "m_loan", joinColumns = @JoinColumn(name = "id"))
    private Long loanId;

    @Column(name = "request", length = 255, nullable = false)
    private byte[] request;

    public BSEIQRequest (Long reqId, Long exchangeId, Long loanId, byte[] request) {
        this.reqId = reqId;
        this.exchangeId = exchangeId;
        this.loanId = loanId;
        this.request = request;
    }

    public BSEIQRequest() {

    }

    public Long getLoanId() { return this.loanId; }

    public Long getReqId() { return this.reqId; }

    public Long getExchangeId() { return this.exchangeId; }

    public byte[] getRequest() {
        return request;
    }

}
