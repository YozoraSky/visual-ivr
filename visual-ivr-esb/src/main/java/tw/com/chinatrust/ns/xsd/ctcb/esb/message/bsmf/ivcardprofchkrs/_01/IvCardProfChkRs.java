//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.03 於 10:53:57 AM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardprofchkrs._01;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RESHDR">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RspCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RESBDY">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="BDYREC" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="FLID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BirthYYYYMMDD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PSWD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MailFiller" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PswdFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ChCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PriId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="AltId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="PaymentFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="VipFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="HomePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="OfficePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="MobilePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="DdFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="EMAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="ContactAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="BillingCycle" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="CorpFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/all>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RESTLR" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "reshdr",
    "resbdy",
    "restlr"
})
@XmlRootElement(name = "ivCardProfChkRs")
public class IvCardProfChkRs {

    @XmlElement(name = "RESHDR", required = true)
    protected IvCardProfChkRs.RESHDR reshdr;
    @XmlElement(name = "RESBDY", required = true)
    protected IvCardProfChkRs.RESBDY resbdy;
    @XmlElement(name = "RESTLR")
    protected IvCardProfChkRs.RESTLR restlr;

    /**
     * 取得 reshdr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvCardProfChkRs.RESHDR }
     *     
     */
    public IvCardProfChkRs.RESHDR getRESHDR() {
        return reshdr;
    }

    /**
     * 設定 reshdr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvCardProfChkRs.RESHDR }
     *     
     */
    public void setRESHDR(IvCardProfChkRs.RESHDR value) {
        this.reshdr = value;
    }

    /**
     * 取得 resbdy 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvCardProfChkRs.RESBDY }
     *     
     */
    public IvCardProfChkRs.RESBDY getRESBDY() {
        return resbdy;
    }

    /**
     * 設定 resbdy 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvCardProfChkRs.RESBDY }
     *     
     */
    public void setRESBDY(IvCardProfChkRs.RESBDY value) {
        this.resbdy = value;
    }

    /**
     * 取得 restlr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvCardProfChkRs.RESTLR }
     *     
     */
    public IvCardProfChkRs.RESTLR getRESTLR() {
        return restlr;
    }

    /**
     * 設定 restlr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvCardProfChkRs.RESTLR }
     *     
     */
    public void setRESTLR(IvCardProfChkRs.RESTLR value) {
        this.restlr = value;
    }


    /**
     * <p>anonymous complex type 的 Java 類別.
     * 
     * <p>下列綱要片段會指定此類別中包含的預期內容.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="BDYREC" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;all>
     *                   &lt;element name="FLID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BirthYYYYMMDD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PSWD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MailFiller" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PswdFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ChCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PriId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="AltId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="PaymentFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="VipFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="HomePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="OfficePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="MobilePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="DdFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="EMAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="ContactAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="BillingCycle" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="CorpFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/all>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "bdyrec"
    })
    public static class RESBDY {

        @XmlElement(name = "BDYREC")
        protected List<IvCardProfChkRs.RESBDY.BDYREC> bdyrec;

        /**
         * Gets the value of the bdyrec property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the bdyrec property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBDYREC().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IvCardProfChkRs.RESBDY.BDYREC }
         * 
         * 
         */
        public List<IvCardProfChkRs.RESBDY.BDYREC> getBDYREC() {
            if (bdyrec == null) {
                bdyrec = new ArrayList<IvCardProfChkRs.RESBDY.BDYREC>();
            }
            return this.bdyrec;
        }


        /**
         * <p>anonymous complex type 的 Java 類別.
         * 
         * <p>下列綱要片段會指定此類別中包含的預期內容.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;all>
         *         &lt;element name="FLID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BirthYYYYMMDD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PSWD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MailFiller" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PswdFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ChCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PriId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="AltId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="PaymentFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="VipFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="HomePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="OfficePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="MobilePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="DdFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="EMAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="ContactAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="BillingCycle" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="CorpFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/all>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {

        })
        public static class BDYREC {

            @XmlElement(name = "FLID")
            protected String flid;
            @XmlElement(name = "BirthYYYYMMDD")
            protected String birthYYYYMMDD;
            @XmlElement(name = "PSWD")
            protected String pswd;
            @XmlElement(name = "NAME")
            protected String name;
            @XmlElement(name = "MailFiller")
            protected String mailFiller;
            @XmlElement(name = "PswdFlag")
            protected String pswdFlag;
            @XmlElement(name = "ChCode")
            protected String chCode;
            @XmlElement(name = "PriId")
            protected String priId;
            @XmlElement(name = "AltId")
            protected String altId;
            @XmlElement(name = "PaymentFlag")
            protected String paymentFlag;
            @XmlElement(name = "VipFlag")
            protected String vipFlag;
            @XmlElement(name = "HomePhone")
            protected String homePhone;
            @XmlElement(name = "OfficePhone")
            protected String officePhone;
            @XmlElement(name = "MobilePhone")
            protected String mobilePhone;
            @XmlElement(name = "DdFlag")
            protected String ddFlag;
            @XmlElement(name = "EMAIL")
            protected String email;
            @XmlElement(name = "ContactAddr")
            protected String contactAddr;
            @XmlElement(name = "BillingCycle")
            protected BigDecimal billingCycle;
            @XmlElement(name = "CorpFlag")
            protected String corpFlag;

            /**
             * 取得 flid 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFLID() {
                return flid;
            }

            /**
             * 設定 flid 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFLID(String value) {
                this.flid = value;
            }

            /**
             * 取得 birthYYYYMMDD 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBirthYYYYMMDD() {
                return birthYYYYMMDD;
            }

            /**
             * 設定 birthYYYYMMDD 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBirthYYYYMMDD(String value) {
                this.birthYYYYMMDD = value;
            }

            /**
             * 取得 pswd 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPSWD() {
                return pswd;
            }

            /**
             * 設定 pswd 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPSWD(String value) {
                this.pswd = value;
            }

            /**
             * 取得 name 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNAME() {
                return name;
            }

            /**
             * 設定 name 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNAME(String value) {
                this.name = value;
            }

            /**
             * 取得 mailFiller 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMailFiller() {
                return mailFiller;
            }

            /**
             * 設定 mailFiller 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMailFiller(String value) {
                this.mailFiller = value;
            }

            /**
             * 取得 pswdFlag 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPswdFlag() {
                return pswdFlag;
            }

            /**
             * 設定 pswdFlag 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPswdFlag(String value) {
                this.pswdFlag = value;
            }

            /**
             * 取得 chCode 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getChCode() {
                return chCode;
            }

            /**
             * 設定 chCode 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setChCode(String value) {
                this.chCode = value;
            }

            /**
             * 取得 priId 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPriId() {
                return priId;
            }

            /**
             * 設定 priId 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPriId(String value) {
                this.priId = value;
            }

            /**
             * 取得 altId 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAltId() {
                return altId;
            }

            /**
             * 設定 altId 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAltId(String value) {
                this.altId = value;
            }

            /**
             * 取得 paymentFlag 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPaymentFlag() {
                return paymentFlag;
            }

            /**
             * 設定 paymentFlag 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPaymentFlag(String value) {
                this.paymentFlag = value;
            }

            /**
             * 取得 vipFlag 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVipFlag() {
                return vipFlag;
            }

            /**
             * 設定 vipFlag 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVipFlag(String value) {
                this.vipFlag = value;
            }

            /**
             * 取得 homePhone 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHomePhone() {
                return homePhone;
            }

            /**
             * 設定 homePhone 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHomePhone(String value) {
                this.homePhone = value;
            }

            /**
             * 取得 officePhone 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOfficePhone() {
                return officePhone;
            }

            /**
             * 設定 officePhone 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOfficePhone(String value) {
                this.officePhone = value;
            }

            /**
             * 取得 mobilePhone 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMobilePhone() {
                return mobilePhone;
            }

            /**
             * 設定 mobilePhone 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMobilePhone(String value) {
                this.mobilePhone = value;
            }

            /**
             * 取得 ddFlag 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDdFlag() {
                return ddFlag;
            }

            /**
             * 設定 ddFlag 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDdFlag(String value) {
                this.ddFlag = value;
            }

            /**
             * 取得 email 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEMAIL() {
                return email;
            }

            /**
             * 設定 email 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEMAIL(String value) {
                this.email = value;
            }

            /**
             * 取得 contactAddr 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getContactAddr() {
                return contactAddr;
            }

            /**
             * 設定 contactAddr 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setContactAddr(String value) {
                this.contactAddr = value;
            }

            /**
             * 取得 billingCycle 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getBillingCycle() {
                return billingCycle;
            }

            /**
             * 設定 billingCycle 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setBillingCycle(BigDecimal value) {
                this.billingCycle = value;
            }

            /**
             * 取得 corpFlag 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCorpFlag() {
                return corpFlag;
            }

            /**
             * 設定 corpFlag 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCorpFlag(String value) {
                this.corpFlag = value;
            }

        }

    }


    /**
     * <p>anonymous complex type 的 Java 類別.
     * 
     * <p>下列綱要片段會指定此類別中包含的預期內容.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="RspCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "rspCode",
        "trnNum"
    })
    public static class RESHDR {

        @XmlElement(name = "RspCode")
        protected String rspCode;
        @XmlElement(name = "TrnNum")
        protected String trnNum;

        /**
         * 取得 rspCode 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRspCode() {
            return rspCode;
        }

        /**
         * 設定 rspCode 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRspCode(String value) {
            this.rspCode = value;
        }

        /**
         * 取得 trnNum 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTrnNum() {
            return trnNum;
        }

        /**
         * 設定 trnNum 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTrnNum(String value) {
            this.trnNum = value;
        }

    }


    /**
     * <p>anonymous complex type 的 Java 類別.
     * 
     * <p>下列綱要片段會指定此類別中包含的預期內容.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class RESTLR {


    }

}
