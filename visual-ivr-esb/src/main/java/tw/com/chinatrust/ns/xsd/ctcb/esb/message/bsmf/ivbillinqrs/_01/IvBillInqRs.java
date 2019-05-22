//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.03 於 10:53:43 AM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivbillinqrs._01;

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
 *                             &lt;element name="BillDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CurrBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="DueAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="PymtDueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="StmtYYYYMM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="CurrBalSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="UnpaidStmtBal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="TotAmtDueSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TotAmtDue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *                             &lt;element name="PymtFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TotTxAmtSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="TotTxAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
@XmlRootElement(name = "ivBillInqRs")
public class IvBillInqRs {

    @XmlElement(name = "RESHDR", required = true)
    protected IvBillInqRs.RESHDR reshdr;
    @XmlElement(name = "RESBDY", required = true)
    protected IvBillInqRs.RESBDY resbdy;
    @XmlElement(name = "RESTLR")
    protected IvBillInqRs.RESTLR restlr;

    /**
     * 取得 reshdr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvBillInqRs.RESHDR }
     *     
     */
    public IvBillInqRs.RESHDR getRESHDR() {
        return reshdr;
    }

    /**
     * 設定 reshdr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvBillInqRs.RESHDR }
     *     
     */
    public void setRESHDR(IvBillInqRs.RESHDR value) {
        this.reshdr = value;
    }

    /**
     * 取得 resbdy 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvBillInqRs.RESBDY }
     *     
     */
    public IvBillInqRs.RESBDY getRESBDY() {
        return resbdy;
    }

    /**
     * 設定 resbdy 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvBillInqRs.RESBDY }
     *     
     */
    public void setRESBDY(IvBillInqRs.RESBDY value) {
        this.resbdy = value;
    }

    /**
     * 取得 restlr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvBillInqRs.RESTLR }
     *     
     */
    public IvBillInqRs.RESTLR getRESTLR() {
        return restlr;
    }

    /**
     * 設定 restlr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvBillInqRs.RESTLR }
     *     
     */
    public void setRESTLR(IvBillInqRs.RESTLR value) {
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
     *                   &lt;element name="BillDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CurrBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="DueAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="PymtDueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="StmtYYYYMM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="CurrBalSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="UnpaidStmtBal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="TotAmtDueSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TotAmtDue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
     *                   &lt;element name="PymtFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TotTxAmtSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="TotTxAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
        protected List<IvBillInqRs.RESBDY.BDYREC> bdyrec;

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
         * {@link IvBillInqRs.RESBDY.BDYREC }
         * 
         * 
         */
        public List<IvBillInqRs.RESBDY.BDYREC> getBDYREC() {
            if (bdyrec == null) {
                bdyrec = new ArrayList<IvBillInqRs.RESBDY.BDYREC>();
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
         *         &lt;element name="BillDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CurrBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="DueAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="PymtDueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="StmtYYYYMM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="CurrBalSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="UnpaidStmtBal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="TotAmtDueSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TotAmtDue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
         *         &lt;element name="PymtFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TotTxAmtSign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="TotTxAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
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
            @XmlElement(name = "BillDate")
            protected String billDate;
            @XmlElement(name = "CurrBalance")
            protected BigDecimal currBalance;
            @XmlElement(name = "DueAmt")
            protected BigDecimal dueAmt;
            @XmlElement(name = "PymtDueDate")
            protected String pymtDueDate;
            @XmlElement(name = "StmtYYYYMM")
            protected String stmtYYYYMM;
            @XmlElement(name = "CurrBalSign")
            protected String currBalSign;
            @XmlElement(name = "UnpaidStmtBal")
            protected BigDecimal unpaidStmtBal;
            @XmlElement(name = "TotAmtDueSign")
            protected String totAmtDueSign;
            @XmlElement(name = "TotAmtDue")
            protected BigDecimal totAmtDue;
            @XmlElement(name = "PymtFlag")
            protected String pymtFlag;
            @XmlElement(name = "TotTxAmtSign")
            protected String totTxAmtSign;
            @XmlElement(name = "TotTxAmt")
            protected BigDecimal totTxAmt;

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
             * 取得 billDate 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBillDate() {
                return billDate;
            }

            /**
             * 設定 billDate 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBillDate(String value) {
                this.billDate = value;
            }

            /**
             * 取得 currBalance 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getCurrBalance() {
                return currBalance;
            }

            /**
             * 設定 currBalance 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setCurrBalance(BigDecimal value) {
                this.currBalance = value;
            }

            /**
             * 取得 dueAmt 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getDueAmt() {
                return dueAmt;
            }

            /**
             * 設定 dueAmt 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setDueAmt(BigDecimal value) {
                this.dueAmt = value;
            }

            /**
             * 取得 pymtDueDate 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPymtDueDate() {
                return pymtDueDate;
            }

            /**
             * 設定 pymtDueDate 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPymtDueDate(String value) {
                this.pymtDueDate = value;
            }

            /**
             * 取得 stmtYYYYMM 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStmtYYYYMM() {
                return stmtYYYYMM;
            }

            /**
             * 設定 stmtYYYYMM 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStmtYYYYMM(String value) {
                this.stmtYYYYMM = value;
            }

            /**
             * 取得 currBalSign 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCurrBalSign() {
                return currBalSign;
            }

            /**
             * 設定 currBalSign 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCurrBalSign(String value) {
                this.currBalSign = value;
            }

            /**
             * 取得 unpaidStmtBal 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getUnpaidStmtBal() {
                return unpaidStmtBal;
            }

            /**
             * 設定 unpaidStmtBal 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setUnpaidStmtBal(BigDecimal value) {
                this.unpaidStmtBal = value;
            }

            /**
             * 取得 totAmtDueSign 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTotAmtDueSign() {
                return totAmtDueSign;
            }

            /**
             * 設定 totAmtDueSign 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTotAmtDueSign(String value) {
                this.totAmtDueSign = value;
            }

            /**
             * 取得 totAmtDue 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotAmtDue() {
                return totAmtDue;
            }

            /**
             * 設定 totAmtDue 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotAmtDue(BigDecimal value) {
                this.totAmtDue = value;
            }

            /**
             * 取得 pymtFlag 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPymtFlag() {
                return pymtFlag;
            }

            /**
             * 設定 pymtFlag 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPymtFlag(String value) {
                this.pymtFlag = value;
            }

            /**
             * 取得 totTxAmtSign 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTotTxAmtSign() {
                return totTxAmtSign;
            }

            /**
             * 設定 totTxAmtSign 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTotTxAmtSign(String value) {
                this.totTxAmtSign = value;
            }

            /**
             * 取得 totTxAmt 特性的值.
             * 
             * @return
             *     possible object is
             *     {@link BigDecimal }
             *     
             */
            public BigDecimal getTotTxAmt() {
                return totTxAmt;
            }

            /**
             * 設定 totTxAmt 特性的值.
             * 
             * @param value
             *     allowed object is
             *     {@link BigDecimal }
             *     
             */
            public void setTotTxAmt(BigDecimal value) {
                this.totTxAmt = value;
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
