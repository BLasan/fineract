package org.apache.fineract.infrastructure.exchange.data;

public class ExchangeConfigurationData {

    private final Integer memberId;
    private final String userName;
    private final String password;
    private final String baseAPIURL;
    private final String applyIPOAPI;
    private final String logoutAPI;
    private final String tokenAPI;
    private final String env;

    public ExchangeConfigurationData(final Integer memberId, final String baseAPIURL, final String password, final String userName,
                                     final String applyIPOAPI, final String logoutAPI, final String tokenAPI, final String env) {
        this.memberId = memberId;
        this.password = password;
        this.baseAPIURL = baseAPIURL;
        this.userName = userName;
        this.applyIPOAPI = applyIPOAPI;
        this.logoutAPI = logoutAPI;
        this.tokenAPI = tokenAPI;
        this.env = env;
    }

    public Integer getMemberId() { return this.memberId; }

    public String getUserName() { return this.userName; }

    public String getPassword() { return this.password; }

    public String getBaseAPIURL() { return this.baseAPIURL; }

    public String getApplyIPOAPI() { return this.applyIPOAPI; }

    public String getLogoutAPI() { return this.logoutAPI; }

    public String getTokenAPI() { return this.tokenAPI; }

    public String getEnv() { return this.env; }

}
