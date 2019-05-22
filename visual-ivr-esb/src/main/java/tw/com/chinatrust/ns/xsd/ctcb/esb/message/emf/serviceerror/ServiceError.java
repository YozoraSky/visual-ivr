//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.02 於 06:20:38 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceerror;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.Error;


/**
 * <p>ServiceError complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="ServiceError">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common}Error"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceError}ExtensionServiceError" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceError", propOrder = {
    "error",
    "extensionServiceError"
})
public class ServiceError {

    @XmlElement(name = "Error", namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", required = true)
    protected Error error;
    @XmlElement(name = "ExtensionServiceError")
    protected AnyContainer extensionServiceError;

    /**
     * 取得 error 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Error }
     *     
     */
    public Error getError() {
        return error;
    }

    /**
     * 設定 error 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Error }
     *     
     */
    public void setError(Error value) {
        this.error = value;
    }

    /**
     * 取得 extensionServiceError 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getExtensionServiceError() {
        return extensionServiceError;
    }

    /**
     * 設定 extensionServiceError 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setExtensionServiceError(AnyContainer value) {
        this.extensionServiceError = value;
    }

}
