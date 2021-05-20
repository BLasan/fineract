package org.apache.fineract.infrastructure.bse.domain;

import org.apache.fineract.infrastructure.core.domain.AbstractPersistableCustom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Demat")
public class BSEDemat extends AbstractPersistableCustom {

    @Column(name = "client_id", unique = true, nullable = false, length = 20)
    private Long clientId;

    @Column(name = "Depository_cd_Depository", nullable = false, length = 11)
    private Integer depository;

    @Column(name = "DPID", nullable = false, length = 8)
    private String depositId;

    @Column(name = "ClientId", nullable = false, length = 8)
    private String clientIdd;

    @Column(name = "PAN No", nullable = false, length = 10)
    private String panNo;

    @Column(name = "Bank Name", length = 6)
    private String bankName;

    @Column(name = "Location", length = 6)
    private String location;

    @Column(name = "Account_UPI_Id", length = 45)
    private String upiId;

    @Column(name = "IFSC Code", length = 11)
    private String ifscCode;

    @Column(name = "ClientType_cd_Category", length = 11)
    private Integer category;

    public BSEDemat() {

    }

    public BSEDemat (Long clientId, Integer depository, String depositId, String clientIdd, String panNo, String bankName,
                     String location, String upiId, String ifscCode, Integer category) {
        this.clientId = clientId;
        this.depository = depository;
        this.depositId = depositId;
        this.clientIdd = clientIdd;
        this.panNo = panNo;
        this.bankName = bankName;
        this.location = location;
        this.upiId = upiId;
        this.ifscCode = ifscCode;
        this.category = category;
    }

    public Long getClientId() { return this.clientId; }

    public Integer getDepository() { return this.depository; }

    public String getDepositId() { return this.depositId; }

    public String getClientIdd() { return this.clientIdd; }

    public String getPanNo() { return this.panNo; }

    public String getBankName() { return this.bankName; }

    public String getLocation() { return this.location; }

    public String getUpiId() { return this.upiId; }

    public String getIfscCode() { return this.ifscCode; }

    public Integer getCategory() { return this.category; }
}
