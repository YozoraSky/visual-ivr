//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.16 於 03:57:28 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardcvvchkrq._01;

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
 *                   &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="SystemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="TrnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   &lt;element name="FUNCD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ExpirDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CVV2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="CSC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "ivCardCVVChkRq")
public class IvCardCVVChkRq {

    @XmlElement(name = "REQHDR", required = true)
    protected IvCardCVVChkRq.REQHDR reqhdr;
    @XmlElement(name = "REQBDY", required = true)
    protected IvCardCVVChkRq.REQBDY reqbdy;

    /**
     * 取得 reqhdr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvCardCVVChkRq.REQHDR }
     *     
     */
    public IvCardCVVChkRq.REQHDR getREQHDR() {
        return reqhdr;
    }

    /**
     * 設定 reqhdr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvCardCVVChkRq.REQHDR }
     *     
     */
    public void setREQHDR(IvCardCVVChkRq.REQHDR value) {
        this.reqhdr = value;
    }

    /**
     * 取得 reqbdy 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvCardCVVChkRq.REQBDY }
     *     
     */
    public IvCardCVVChkRq.REQBDY getREQBDY() {
        return reqbdy;
    }

    /**
     * 設定 reqbdy 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvCardCVVChkRq.REQBDY }
     *     
     */
    public void setREQBDY(IvCardCVVChkRq.REQBDY value) {
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
     *         &lt;element name="FUNCD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ExpirDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CVV2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="CSC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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

        @XmlElement(name = "FUNCD")
        protected String funcd;
        @XmlElement(name = "CardNo")
        protected String cardNo;
        @XmlElement(name = "ExpirDate")
        protected String expirDate;
        @XmlElement(name = "CVV2")
        protected String cvv2;
        @XmlElement(name = "CSC")
        protected String csc;

        /**
         * 取得 funcd 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFUNCD() {
            return funcd;
        }

        /**
         * 設定 funcd 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFUNCD(String value) {
            this.funcd = value;
        }

        /**
         * 取得 cardNo 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCardNo() {
            return cardNo;
        }

        /**
         * 設定 cardNo 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCardNo(String value) {
            this.cardNo = value;
        }

        /**
         * 取得 expirDate 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExpirDate() {
            return expirDate;
        }

        /**
         * 設定 expirDate 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExpirDate(String value) {
            this.expirDate = value;
        }

        /**
         * 取得 cvv2 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCVV2() {
            return cvv2;
        }

        /**
         * 設定 cvv2 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCVV2(String value) {
            this.cvv2 = value;
        }

        /**
         * 取得 csc 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCSC() {
            return csc;
        }

        /**
         * 設定 csc 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCSC(String value) {
            this.csc = value;
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
     *         &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="SystemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="TrnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "systemId",
        "trnCode"
    })
    public static class REQHDR {

        @XmlElement(name = "TrnNum")
        protected String trnNum;
        @XmlElement(name = "SystemId")
        protected String systemId;
        @XmlElement(name = "TrnCode")
        protected String trnCode;

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
         * 取得 systemId 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSystemId() {
            return systemId;
        }

        /**
         * 設定 systemId 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSystemId(String value) {
            this.systemId = value;
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

    }

}
