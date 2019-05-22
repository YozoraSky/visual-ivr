//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.16 於 03:57:33 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.bsmf.ivcardcvvchkrs._01;

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
@XmlRootElement(name = "ivCardCVVChkRs")
public class IvCardCVVChkRs {

    @XmlElement(name = "RESHDR", required = true)
    protected IvCardCVVChkRs.RESHDR reshdr;
    @XmlElement(name = "RESBDY", required = true)
    protected IvCardCVVChkRs.RESBDY resbdy;
    @XmlElement(name = "RESTLR")
    protected IvCardCVVChkRs.RESTLR restlr;

    /**
     * 取得 reshdr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvCardCVVChkRs.RESHDR }
     *     
     */
    public IvCardCVVChkRs.RESHDR getRESHDR() {
        return reshdr;
    }

    /**
     * 設定 reshdr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvCardCVVChkRs.RESHDR }
     *     
     */
    public void setRESHDR(IvCardCVVChkRs.RESHDR value) {
        this.reshdr = value;
    }

    /**
     * 取得 resbdy 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvCardCVVChkRs.RESBDY }
     *     
     */
    public IvCardCVVChkRs.RESBDY getRESBDY() {
        return resbdy;
    }

    /**
     * 設定 resbdy 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvCardCVVChkRs.RESBDY }
     *     
     */
    public void setRESBDY(IvCardCVVChkRs.RESBDY value) {
        this.resbdy = value;
    }

    /**
     * 取得 restlr 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link IvCardCVVChkRs.RESTLR }
     *     
     */
    public IvCardCVVChkRs.RESTLR getRESTLR() {
        return restlr;
    }

    /**
     * 設定 restlr 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link IvCardCVVChkRs.RESTLR }
     *     
     */
    public void setRESTLR(IvCardCVVChkRs.RESTLR value) {
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
        protected List<IvCardCVVChkRs.RESBDY.BDYREC> bdyrec;

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
         * {@link IvCardCVVChkRs.RESBDY.BDYREC }
         * 
         * 
         */
        public List<IvCardCVVChkRs.RESBDY.BDYREC> getBDYREC() {
            if (bdyrec == null) {
                bdyrec = new ArrayList<IvCardCVVChkRs.RESBDY.BDYREC>();
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
