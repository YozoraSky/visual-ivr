//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.13 於 05:21:45 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.csbillresendinqrq._01;

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
 *         &lt;element name="REQHDR">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TrnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CtryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="REQBDY">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="CustId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CustName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CustBirthDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BillType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="InqStartDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="InqEndDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "reqhdr",
    "reqbdy"
})
@XmlRootElement(name = "csBillReSendInqRq")
public class CsBillReSendInqRq {
    @XmlElement(name = "REQHDR", required = true)
    protected CsBillReSendInqRq.REQHDR reqhdr;
    @XmlElement(name = "REQBDY", required = true)
    protected CsBillReSendInqRq.REQBDY reqbdy;

    /**
     * 取得 reqhdr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link CsBillReSendInqRq.REQHDR }
     *     
     */
    public CsBillReSendInqRq.REQHDR getREQHDR() {
        return reqhdr;
    }

    /**
     * 設定 reqhdr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link CsBillReSendInqRq.REQHDR }
     *     
     */
    public void setREQHDR(CsBillReSendInqRq.REQHDR value) {
        this.reqhdr = value;
    }

    /**
     * 取得 reqbdy 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link CsBillReSendInqRq.REQBDY }
     *     
     */
    public CsBillReSendInqRq.REQBDY getREQBDY() {
        return reqbdy;
    }

    /**
     * 設定 reqbdy 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link CsBillReSendInqRq.REQBDY }
     *     
     */
    public void setREQBDY(CsBillReSendInqRq.REQBDY value) {
        this.reqbdy = value;
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
     *         &lt;element name="CustId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CustName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CustBirthDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BillType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="InqStartDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="InqEndDt" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class REQBDY {

        @XmlElement(name = "CustId", required = true)
        protected String custId;
        @XmlElement(name = "CustName", required = true)
        protected String custName;
        @XmlElement(name = "CustBirthDt", required = true)
        protected String custBirthDt;
        @XmlElement(name = "BillType", required = true)
        protected String billType;
        @XmlElement(name = "InqStartDt", required = true)
        protected String inqStartDt;
        @XmlElement(name = "InqEndDt", required = true)
        protected String inqEndDt;

        /**
         * 取得 custId 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustId() {
            return custId;
        }

        /**
         * 設定 custId 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustId(String value) {
            this.custId = value;
        }

        /**
         * 取得 custName 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustName() {
            return custName;
        }

        /**
         * 設定 custName 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustName(String value) {
            this.custName = value;
        }

        /**
         * 取得 custBirthDt 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustBirthDt() {
            return custBirthDt;
        }

        /**
         * 設定 custBirthDt 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustBirthDt(String value) {
            this.custBirthDt = value;
        }

        /**
         * 取得 billType 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBillType() {
            return billType;
        }

        /**
         * 設定 billType 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBillType(String value) {
            this.billType = value;
        }

        /**
         * 取得 inqStartDt 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInqStartDt() {
            return inqStartDt;
        }

        /**
         * 設定 inqStartDt 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInqStartDt(String value) {
            this.inqStartDt = value;
        }

        /**
         * 取得 inqEndDt 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInqEndDt() {
            return inqEndDt;
        }

        /**
         * 設定 inqEndDt 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInqEndDt(String value) {
            this.inqEndDt = value;
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
     *         &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TrnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CtryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "trnNum",
        "trnCode",
        "ctryCode"
    })
    public static class REQHDR {

        @XmlElement(name = "TrnNum", required = true)
        protected String trnNum;
        @XmlElement(name = "TrnCode")
        protected String trnCode;
        @XmlElement(name = "CtryCode")
        protected String ctryCode;

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

        /**
         * 取得 trnCode 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTrnCode() {
            return trnCode;
        }

        /**
         * 設定 trnCode 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTrnCode(String value) {
            this.trnCode = value;
        }

        /**
         * 取得 ctryCode 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCtryCode() {
            return ctryCode;
        }

        /**
         * 設定 ctryCode 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCtryCode(String value) {
            this.ctryCode = value;
        }

    }

}
