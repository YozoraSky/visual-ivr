//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.16 於 03:57:23 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.csbillresendntfyrs._01;

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
 *                   &lt;element name="RspCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *                   &lt;element name="BDYREC" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
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
    "resbdy"
})
@XmlRootElement(name = "csBillReSendNtfyRs")
public class CsBillReSendNtfyRs {

    @XmlElement(name = "RESHDR", required = true)
    protected CsBillReSendNtfyRs.RESHDR reshdr;
    @XmlElement(name = "RESBDY", required = true)
    protected CsBillReSendNtfyRs.RESBDY resbdy;

    /**
     * 取得 reshdr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link CsBillReSendNtfyRs.RESHDR }
     *     
     */
    public CsBillReSendNtfyRs.RESHDR getRESHDR() {
        return reshdr;
    }

    /**
     * 設定 reshdr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link CsBillReSendNtfyRs.RESHDR }
     *     
     */
    public void setRESHDR(CsBillReSendNtfyRs.RESHDR value) {
        this.reshdr = value;
    }

    /**
     * 取得 resbdy 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link CsBillReSendNtfyRs.RESBDY }
     *     
     */
    public CsBillReSendNtfyRs.RESBDY getRESBDY() {
        return resbdy;
    }

    /**
     * 設定 resbdy 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link CsBillReSendNtfyRs.RESBDY }
     *     
     */
    public void setRESBDY(CsBillReSendNtfyRs.RESBDY value) {
        this.resbdy = value;
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
     *         &lt;element name="BDYREC" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
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
        protected List<Object> bdyrec;

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
         * {@link Object }
         * 
         * 
         */
        public List<Object> getBDYREC() {
            if (bdyrec == null) {
                bdyrec = new ArrayList<Object>();
            }
            return this.bdyrec;
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
     *         &lt;element name="RspCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TrnNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
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

        @XmlElement(name = "RspCode", required = true)
        protected String rspCode;
        @XmlElement(name = "TrnNum", required = true)
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

}
