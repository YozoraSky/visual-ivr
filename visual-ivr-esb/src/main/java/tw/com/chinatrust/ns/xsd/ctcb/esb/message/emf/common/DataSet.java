//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.02 於 06:20:38 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DataSet complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="DataSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NoOfRecord" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common}Data" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common}Cursor" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common}ExtentionDataSet" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSet", propOrder = {
    "noOfRecord",
    "data",
    "cursor",
    "extentionDataSet"
})
public class DataSet {

    @XmlElement(name = "NoOfRecord")
    protected BigInteger noOfRecord;
    @XmlElement(name = "Data")
    protected List<AnyContainer> data;
    @XmlElement(name = "Cursor")
    protected Cursor cursor;
    @XmlElement(name = "ExtentionDataSet")
    protected AnyContainer extentionDataSet;

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
     * Gets the value of the data property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the data property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnyContainer }
     * 
     * 
     */
    public List<AnyContainer> getData() {
        if (data == null) {
            data = new ArrayList<AnyContainer>();
        }
        return this.data;
    }

    /**
     * 取得 cursor 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Cursor }
     *     
     */
    public Cursor getCursor() {
        return cursor;
    }

    /**
     * 設定 cursor 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Cursor }
     *     
     */
    public void setCursor(Cursor value) {
        this.cursor = value;
    }

    /**
     * 取得 extentionDataSet 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getExtentionDataSet() {
        return extentionDataSet;
    }

    /**
     * 設定 extentionDataSet 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setExtentionDataSet(AnyContainer value) {
        this.extentionDataSet = value;
    }

}
