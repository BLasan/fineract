package org.apache.fineract.infrastructure.exchange.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "m_bse_user_token")
public class ExchangeUserTokenData extends AbstractPersistable {

    @Column(name = "memberId", length = 10, unique = true)
    private String memberId;

    @Column(name = "token", length = 50)
    private String token;

    @Column(name = "tokenType", length = 20)
    private String tokenType;

    @Column(name = "expireDate")
    private Date expires;

    @Column(name = "loginId", length = 15, unique = true)
    private String loginId;

//    @Column(name = "branchCode", length = 10)
//    private final String branchCode;
//
//    @Column(name = "loginId", length = 15)
//    private final String loginId;
//
//    @Column(name = "errorCode", length = 3)
//    private final String errorCode;
//
//    @Column(name = "message", length = 250)
//    private final String message;

    public ExchangeUserTokenData() {

    }

    public ExchangeUserTokenData(String memberId, String token, String tokenType, Date expires, String loginId) {
        this.memberId = memberId;
        this.token = token;
        this.tokenType = tokenType;
        this.expires = expires;
        this.loginId = loginId;
    }

    public String getLoginId() { return this.loginId; }

    public String getMemberId() { return this.memberId; }

    public String getToken() { return this.token; }

    public String getTokenType() { return this.tokenType; }

    public Date getExpires() { return this.expires; }
}
