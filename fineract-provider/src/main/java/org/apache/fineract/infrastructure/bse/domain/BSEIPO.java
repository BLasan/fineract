package org.apache.fineract.infrastructure.bse.domain;

import javax.persistence.*;

@Entity
@Table(name = "IPO")
public class BSEIPO {

    @Column(name = "group_id", unique = true, length = 20, nullable = false)
    @OneToOne
    @JoinTable(name = "m_group", joinColumns = @JoinColumn(name = "id"))
    private Long groupId;

    @Column(name = "ScripId", nullable = false, length = 10)
    private String scriptId;

    @Column(name = "ApplicationNo", nullable = false, length = 16)
    private String applicationNo;

    @Column(name = "ClientType_cd_Category", length = 11)
    private Integer category;

    @Column(name = "YesNo_cd_ChequeRecievedFlag", length = 11)
    private Integer checkReceive;

    @Column(name = "ASBA_UPI_Id", length = 11)
    private Integer upiId;

    @Column(name = "ReferenceNo", length = 16)
    private String referenceNo;

    public BSEIPO() {

    }

    public BSEIPO(Long groupId, String scriptId, String applicationNo, Integer category, Integer checkReceive,
                  Integer upiId, String referenceNo) {
        this.applicationNo = applicationNo;
        this.groupId = groupId;
        this.scriptId = scriptId;
        this.category = category;
        this.checkReceive = checkReceive;
        this.upiId = upiId;
        this.referenceNo =referenceNo;
    }

    public Long getGroupId() { return this.groupId; }

    public String getScriptId() { return this.scriptId; }

    public String getApplicationNo() { return this.applicationNo; }

    public Integer getCategory() { return this.category; }

    public Integer getCheckReceive() { return this.checkReceive; }

    public Integer getUpiId() { return this.upiId; }

    public String getReferenceNo() { return this.referenceNo; }
}
