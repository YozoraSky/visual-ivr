//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.04 於 02:24:57 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivbillinqrq._01;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;


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
 *                   &lt;element name="SystemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *                   &lt;element name="IDNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlRootElement(name = "ivBillInqRq")
public class IvBillInqRq {
    @XmlElement(name = "REQHDR", required = true)
    protected IvBillInqRq.REQHDR reqhdr;
    @XmlElement(name = "REQBDY", required = true)
    protected IvBillInqRq.REQBDY reqbdy;

    /**
     * 取得 reqhdr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvBillInqRq.REQHDR }
     *     
     */
    public IvBillInqRq.REQHDR getREQHDR() {
        return reqhdr;
    }

    /**
     * 設定 reqhdr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvBillInqRq.REQHDR }
     *     
     */
    public void setREQHDR(IvBillInqRq.REQHDR value) {
        this.reqhdr = value;
    }

    /**
     * 取得 reqbdy 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvBillInqRq.REQBDY }
     *     
     */
    public IvBillInqRq.REQBDY getREQBDY() {
        return reqbdy;
    }

    /**
     * 設定 reqbdy 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvBillInqRq.REQBDY }
     *     
     */
    public void setREQBDY(IvBillInqRq.REQBDY value) {
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
     *         &lt;element name="IDNO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        @XmlElement(name = "IDNO")
        protected String idno;

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
         * 取得 idno 特性的值.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIDNO() {
            return idno;
        }

        /**
         * 設定 idno 特性的值.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIDNO(String value) {
            this.idno = value;
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
     *         &lt;element name="SystemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "systemId",
        "trnNum",
        "trnCode"
    })
    public static class REQHDR {

        @XmlElement(name = "SystemId")
        protected String systemId;
        @XmlElement(name = "TrnNum")
        protected String trnNum;
        @XmlElement(name = "TrnCode")
        protected String trnCode;

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

    }

}
