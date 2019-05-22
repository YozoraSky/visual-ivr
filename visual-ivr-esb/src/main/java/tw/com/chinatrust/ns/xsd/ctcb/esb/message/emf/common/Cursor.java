//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.02 於 06:20:38 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Cursor complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="Cursor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReferenceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NoOfRecord" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="MoreRecord" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PageNo" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="RecordPerPage" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="TotalPage" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common}ExtensionCursor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cursor", propOrder = {
    "referenceID",
    "noOfRecord",
    "moreRecord",
    "pageNo",
    "recordPerPage",
    "totalPage",
    "extensionCursor"
})
public class Cursor {

    @XmlElement(name = "ReferenceID")
    protected String referenceID;
    @XmlElement(name = "NoOfRecord")
    protected BigInteger noOfRecord;
    @XmlElement(name = "MoreRecord")
    protected Boolean moreRecord;
    @XmlElement(name = "PageNo")
    protected BigInteger pageNo;
    @XmlElement(name = "RecordPerPage")
    protected BigInteger recordPerPage;
    @XmlElement(name = "TotalPage")
    protected BigInteger totalPage;
    @XmlElement(name = "ExtensionCursor")
    protected AnyContainer extensionCursor;

    /**
     * 取得 referenceID 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceID() {
        return referenceID;
    }

    /**
     * 設定 referenceID 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceID(String value) {
        this.referenceID = value;
    }

    /**
     * 取得 noOfRecord 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNoOfRecord() {
        return noOfRecord;
    }

    /**
     * 設定 noOfRecord 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNoOfRecord(BigInteger value) {
        this.noOfRecord = value;
    }

    /**
     * 取得 moreRecord 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMoreRecord() {
        return moreRecord;
    }

    /**
     * 設定 moreRecord 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMoreRecord(Boolean value) {
        this.moreRecord = value;
    }

    /**
     * 取得 pageNo 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPageNo() {
        return pageNo;
    }

    /**
     * 設定 pageNo 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPageNo(BigInteger value) {
        this.pageNo = value;
    }

    /**
     * 取得 recordPerPage 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRecordPerPage() {
        return recordPerPage;
    }

    /**
     * 設定 recordPerPage 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRecordPerPage(BigInteger value) {
        this.recordPerPage = value;
    }

    /**
     * 取得 totalPage 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalPage() {
        return totalPage;
    }

    /**
     * 設定 totalPage 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalPage(BigInteger value) {
        this.totalPage = value;
    }

    /**
     * 取得 extensionCursor 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getExtensionCursor() {
        return extensionCursor;
    }

    /**
     * 設定 extensionCursor 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setExtensionCursor(AnyContainer value) {
        this.extensionCursor = value;
    }

}
