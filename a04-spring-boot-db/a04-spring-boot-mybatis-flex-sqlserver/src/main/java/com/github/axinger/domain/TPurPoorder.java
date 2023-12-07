package com.github.axinger.domain;

import lombok.Data;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

/**
 * 实体类。
 *
 * @author mybatis-flex-helper automatic generation
 * @since 1.0
 */
@Data
@Table(value = "T_PUR_POORDER")
public class TPurPoorder {

    @Id(keyType = KeyType.Auto)
    private Integer fid;

    @Column(value = "FBILLTYPEID")
    private String fbilltypeid;

    @Column(value = "FBILLNO")
    private String fbillno;

    @Column(value = "FDATE")
    private java.util.Date fdate;

    @Column(value = "FSUPPLIERID")
    private Integer fsupplierid;

    @Column(value = "FPURCHASEORGID")
    private Integer fpurchaseorgid;

    @Column(value = "FPURCHASERGROUPID")
    private Integer fpurchasergroupid;

    @Column(value = "FPURCHASEDEPTID")
    private Integer fpurchasedeptid;

    @Column(value = "FPURCHASERID")
    private Integer fpurchaserid;

    @Column(value = "FCREATORID")
    private Integer fcreatorid;

    @Column(value = "FCREATEDATE")
    private java.util.Date fcreatedate;

    @Column(value = "FMODIFIERID")
    private Integer fmodifierid;

    @Column(value = "FMODIFYDATE")
    private java.util.Date fmodifydate;

    @Column(value = "FDOCUMENTSTATUS")
    private String fdocumentstatus;

    @Column(value = "FAPPROVERID")
    private Integer fapproverid;

    @Column(value = "FAPPROVEDATE")
    private java.util.Date fapprovedate;

    @Column(value = "FCLOSESTATUS")
    private String fclosestatus;

    @Column(value = "FCLOSERID")
    private Integer fcloserid;

    @Column(value = "FCLOSEDATE")
    private java.util.Date fclosedate;

    @Column(value = "FCANCELSTATUS")
    private String fcancelstatus;

    @Column(value = "FCANCELLERID")
    private Integer fcancellerid;

    @Column(value = "FCANCELDATE")
    private java.util.Date fcanceldate;

    @Column(value = "FPROVIDERID")
    private Integer fproviderid;

    @Column(value = "FSETTLEID")
    private Integer fsettleid;

    @Column(value = "FCHARGEID")
    private Integer fchargeid;

    @Column(value = "FVERSIONNO")
    private String fversionno;

    @Column(value = "FCHANGEREASON")
    private String fchangereason;

    @Column(value = "FCHANGEDATE")
    private java.util.Date fchangedate;

    @Column(value = "FCHANGERID")
    private Integer fchangerid;

    @Column(value = "FISCONVERT")
    private String fisconvert;

    @Column(value = "FPURCATALOGID")
    private Integer fpurcatalogid;

    @Column(value = "FBUSINESSTYPE")
    private String fbusinesstype;

    @Column(value = "FPROVIDERADDRESS")
    private String fprovideraddress;

    @Column(value = "FOBJECTTYPEID")
    private String fobjecttypeid;

    @Column(value = "FASSIGNSUPPLIERID")
    private Integer fassignsupplierid;

    @Column(value = "FCORRESPONDORGID")
    private Integer fcorrespondorgid;

    @Column(value = "FPROVIDERCONTACT")
    private String fprovidercontact;

    @Column(value = "FNETORDERBILLNO")
    private String fnetorderbillno;

    @Column(value = "FNETORDERBILLID")
    private Integer fnetorderbillid;

    @Column(value = "FCONFIRMSTATUS")
    private String fconfirmstatus;

    @Column(value = "FCONFIRMERID")
    private Integer fconfirmerid;

    @Column(value = "FCONFIRMDATE")
    private java.util.Date fconfirmdate;

    @Column(value = "FPROVIDERCONTACTID")
    private Integer fprovidercontactid;

    @Column(value = "FBILLTYPEIDVM")
    private String fbilltypeidvm;

    @Column(value = "FCHANGESTATUS")
    private String fchangestatus;

    @Column(value = "FSOURCEBILLNO")
    private String fsourcebillno;

    @Column(value = "F_XPJT_TEXT")
    private String fXpjtText;

    @Column(value = "FACCTYPE")
    private String facctype;

    @Column(value = "FRELREQSTATUS")
    private String frelreqstatus;

    @Column(value = "FISTONGBU")
    private String fistongbu;

    @Column(value = "FISTONGBU1")
    private String fistongbu1;

    @Column(value = "FORDERID")
    private String forderid;

    @Column(value = "FSEQ_YPK")
    private Integer fseqYpk;

    @Column(value = "FSTOCKID_YPK")
    private Integer fstockidYpk;

    @Column(value = "FMANUALCLOSE")
    private String fmanualclose;

    @Column(value = "FPROVIDEREMAIL")
    private String fprovideremail;

    @Column(value = "F_QCET_CHECKBOX")
    private String fQcetCheckbox;

    @Column(value = "F_QSUA_TEXT")
    private String fQsuaText;


}
