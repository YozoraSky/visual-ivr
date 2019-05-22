//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.02 於 06:20:38 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceerror.ServiceError;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader.ServiceHeader;


/**
 * <p>ServiceEnvelope complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="ServiceEnvelope">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader}ServiceHeader"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceBody}ServiceBody" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceError}ServiceError" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceEnvelope}ExtensionServiceEnvelope" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceEnvelope", propOrder = {
    "serviceHeader",
    "serviceBody",
    "serviceError",
    "extensionServiceEnvelope"
})
@XmlRootElement(name = "ServiceEnvelope")
public class ServiceEnvelope {

    @XmlElement(name = "ServiceHeader", namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", required = true)
    protected ServiceHeader serviceHeader;
    @XmlElement(name = "ServiceBody", namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceBody")
    protected AnyContainer serviceBody;
    @XmlElement(name = "ServiceError", namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceError")
    protected ServiceError serviceError;
    @XmlElement(name = "ExtensionServiceEnvelope")
    protected AnyContainer extensionServiceEnvelope;

    /**
     * 取得 serviceHeader 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link ServiceHeader }
     *     
     */
    public ServiceHeader getServiceHeader() {
        return serviceHeader;
    }

    /**
     * 設定 serviceHeader 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceHeader }
     *     
     */
    public void setServiceHeader(ServiceHeader value) {
        this.serviceHeader = value;
    }

    /**
     * 取得 serviceBody 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getServiceBody() {
        return serviceBody;
    }

    /**
     * 設定 serviceBody 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setServiceBody(AnyContainer value) {
        this.serviceBody = value;
    }

    /**
     * 取得 serviceError 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link ServiceError }
     *     
     */
    public ServiceError getServiceError() {
        return serviceError;
    }

    /**
     * 設定 serviceError 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceError }
     *     
     */
    public void setServiceError(ServiceError value) {
        this.serviceError = value;
    }

    /**
     * 取得 extensionServiceEnvelope 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getExtensionServiceEnvelope() {
        return extensionServiceEnvelope;
    }

    /**
     * 設定 extensionServiceEnvelope 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setExtensionServiceEnvelope(AnyContainer value) {
        this.extensionServiceEnvelope = value;
    }

}
