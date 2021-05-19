package org.apache.fineract.infrastructure.bse.data;

public class BSEConfigurationData {

    private final String memberCode;
    private final String loginId;
    private final String password;
    private final String ibbsId;

    public BSEConfigurationData(final String memberCode, final String loginId, final String password,
                                final String ibbsId) {
        this.memberCode = memberCode;
        this.loginId = loginId;
        this.password = password;
        this.ibbsId = ibbsId;
    }

    public String getMemberCode() { return this.memberCode; }

    public String getLoginId() { return this.loginId; }

    public String getPassword() { return this.password; }

    public String getIbbsId() { return this.ibbsId; }
}
