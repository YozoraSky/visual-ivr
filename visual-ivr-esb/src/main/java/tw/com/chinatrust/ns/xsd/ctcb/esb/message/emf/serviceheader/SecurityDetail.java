//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.02 於 06:20:38 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;


/**
 * <p>SecurityDetail complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="SecurityDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader}SecurityContext" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader}MessageIntegrity" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader}ExtensionSecurityDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecurityDetail", propOrder = {
    "securityContext",
    "messageIntegrity",
    "extensionSecurityDetail"
})
public class SecurityDetail {

    @XmlElement(name = "SecurityContext")
    protected SecurityContext securityContext;
    @XmlElement(name = "MessageIntegrity")
    protected MessageIntegrity messageIntegrity;
    @XmlElement(name = "ExtensionSecurityDetail")
    protected AnyContainer extensionSecurityDetail;

    /**
     * 取得 securityContext 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link SecurityContext }
     *     
     */
    public SecurityContext getSecurityContext() {
        return securityContext;
    }

    /**
     * 設定 securityContext 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityContext }
     *     
     */
    public void setSecurityContext(SecurityContext value) {
        this.securityContext = value;
    }

    /**
     * 取得 messageIntegrity 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link MessageIntegrity }
     *     
     */
    public MessageIntegrity getMessageIntegrity() {
        return messageIntegrity;
    }

    /**
     * 設定 messageIntegrity 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageIntegrity }
     *     
     */
    public void setMessageIntegrity(MessageIntegrity value) {
        this.messageIntegrity = value;
    }

    /**
     * 取得 extensionSecurityDetail 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getExtensionSecurityDetail() {
        return extensionSecurityDetail;
    }

    /**
     * 設定 extensionSecurityDetail 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setExtensionSecurityDetail(AnyContainer value) {
        this.extensionSecurityDetail = value;
    }

}
