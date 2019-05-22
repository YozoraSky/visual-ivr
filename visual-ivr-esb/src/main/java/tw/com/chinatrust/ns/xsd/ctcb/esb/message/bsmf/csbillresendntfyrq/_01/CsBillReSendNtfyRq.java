//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.16 於 03:55:44 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.csbillresendntfyrq._01;

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
 *         &lt;element name="REQBDY" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="SysSeqNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CycleDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ReSendEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="ReSendTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "csBillReSendNtfyRq")
public class CsBillReSendNtfyRq {

    @XmlElement(name = "REQHDR", required = true)
    protected CsBillReSendNtfyRq.REQHDR reqhdr;
    @XmlElement(name = "REQBDY", required = true)
    protected List<CsBillReSendNtfyRq.REQBDY> reqbdy;

    /**
     * 取得 reqhdr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link CsBillReSendNtfyRq.REQHDR }
     *     
     */
    public CsBillReSendNtfyRq.REQHDR getREQHDR() {
        return reqhdr;
    }

    /**
     * 設定 reqhdr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link CsBillReSendNtfyRq.REQHDR }
     *     
     */
    public void setREQHDR(CsBillReSendNtfyRq.REQHDR value) {
        this.reqhdr = value;
    }

    /**
     * Gets the value of the reqbdy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reqbdy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getREQBDY().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CsBillReSendNtfyRq.REQBDY }
     * 
     * 
     */
    public List<CsBillReSendNtfyRq.REQBDY> getREQBDY() {
        if (reqbdy == null) {
            reqbdy = new ArrayList<CsBillReSendNtfyRq.REQBDY>();
        }
        return this.reqbdy;
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
     *         &lt;element name="SysSeqNo" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CycleDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ReSendEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="ReSendTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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

        @XmlElement(name = "SysSeqNo", required = true)
        protected String sysSeqNo;
        @XmlElement(name = "CycleDate")
        protected String cycleDate;
        @XmlElement(name = "ReSendEmail")
        protected String reSendEmail;
        @XmlElement(name = "ReSendTel")
        protected String reSendTel;

        /**
         * 取得 sysSeqNo 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSysSeqNo() {
            return sysSeqNo;
        }

        /**
         * 設定 sysSeqNo 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSysSeqNo(String value) {
            this.sysSeqNo = value;
        }

        /**
         * 取得 cycleDate 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCycleDate() {
            return cycleDate;
        }

        /**
         * 設定 cycleDate 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCycleDate(String value) {
            this.cycleDate = value;
        }

        /**
         * 取得 reSendEmail 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReSendEmail() {
            return reSendEmail;
        }

        /**
         * 設定 reSendEmail 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReSendEmail(String value) {
            this.reSendEmail = value;
        }

        /**
         * 取得 reSendTel 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReSendTel() {
            return reSendTel;
        }

        /**
         * 設定 reSendTel 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReSendTel(String value) {
            this.reSendTel = value;
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
