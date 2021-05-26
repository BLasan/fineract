package org.apache.fineract.infrastructure.exchange.data;

public class ExchangeConfigurationData {

    private final Integer memberId;
    private final String userName;
    private final String password;
    private final String baseAPIURL;

    public ExchangeConfigurationData(final Integer memberId, final String baseAPIURL, final String password, final String userName) {
        this.memberId = memberId;
        this.password = password;
        this.baseAPIURL = baseAPIURL;
        this.userName = userName;
    }

    public Integer getMemberId() { return this.memberId; }

    public String getUserName() { return this.userName; }

    public String getPassword() { return this.password; }

    public String getBaseAPIURL() { return this.baseAPIURL; }
}
