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
 * <p>SessionControl complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="SessionControl">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SessionKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SessionData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader}ExtensionSessionControl" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SessionControl", propOrder = {
    "sessionKey",
    "sessionData",
    "extensionSessionControl"
})
public class SessionControl {

    @XmlElement(name = "SessionKey", required = true)
    protected String sessionKey;
    @XmlElement(name = "SessionData")
    protected String sessionData;
    @XmlElement(name = "ExtensionSessionControl")
    protected AnyContainer extensionSessionControl;

    /**
     * 取得 sessionKey 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * 設定 sessionKey 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionKey(String value) {
        this.sessionKey = value;
    }

    /**
     * 取得 sessionData 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionData() {
        return sessionData;
    }

    /**
     * 設定 sessionData 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionData(String value) {
        this.sessionData = value;
    }

    /**
     * 取得 extensionSessionControl 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getExtensionSessionControl() {
        return extensionSessionControl;
    }

    /**
     * 設定 extensionSessionControl 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setExtensionSessionControl(AnyContainer value) {
        this.extensionSessionControl = value;
    }

}
