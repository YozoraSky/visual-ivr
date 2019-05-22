//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.02 於 06:20:38 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RqDetail complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="RqDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common}Data" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common}Cursor" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common}ExtensionRqDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RqDetail", propOrder = {
    "data",
    "cursor",
    "extensionRqDetail"
})
public class RqDetail {

    @XmlElement(name = "Data")
    protected AnyContainer data;
    @XmlElement(name = "Cursor")
    protected Cursor cursor;
    @XmlElement(name = "ExtensionRqDetail")
    protected AnyContainer extensionRqDetail;

    /**
     * 取得 data 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getData() {
        return data;
    }

    /**
     * 設定 data 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setData(AnyContainer value) {
        this.data = value;
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
     * 取得 extensionRqDetail 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getExtensionRqDetail() {
        return extensionRqDetail;
    }

    /**
     * 設定 extensionRqDetail 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setExtensionRqDetail(AnyContainer value) {
        this.extensionRqDetail = value;
    }

}
