package org.apache.fineract.infrastructure.exchange.data;

public class ExchangeLoginResponseData {

    private final String memberCode;
    private final String loginId;
    private final String branchCode;
    private final String token;
    private final String errorCode;
    private final String message;

    public ExchangeLoginResponseData (final String memberCode, final String loginId, final String branchCode, final String token,
                                      final String errorCode, final String message) {
        this.branchCode = branchCode;
        this.loginId = loginId;
        this.memberCode = memberCode;
        this.errorCode = errorCode;
        this.message = message;
        this.token = token;
    }

    public String getLoginId() { return this.loginId; }

    public String getMemberCode() { return this.memberCode; }

    public String getToken() { return this.token; }

    public String getErrorCode() { return this.errorCode; }

    public String getBranchCode() { return this.branchCode; }

    public String getMessage() { return this.message; }
}
