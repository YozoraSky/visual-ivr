//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.02 於 06:20:38 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;


/**
 * <p>ServiceHeader complex type 的 Java 類別.
 * 
 * <p>下列綱要片段會指定此類別中包含的預期內容.
 * 
 * <pre>
 * &lt;complexType name="ServiceHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StandardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StandardVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TrackingID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChannelID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrelationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RqTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="RsTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="RqExpiration" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ServiceRouting" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RetryCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="RetryInterval" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="StatusCode" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader}SecurityDetail" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader}SessionControl" minOccurs="0"/>
 *         &lt;element name="InternalData" type="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common}AnyContainer" minOccurs="0"/>
 *         &lt;element ref="{http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader}ExtensionServiceHeader" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceHeader", propOrder = {
    "standardType",
    "standardVersion",
    "trackingID",
    "serviceName",
    "serviceVersion",
    "sourceID",
    "channelID",
    "transactionID",
    "correlationID",
    "rqTimestamp",
    "rsTimestamp",
    "rqExpiration",
    "serviceRouting",
    "retryCount",
    "retryInterval",
    "statusCode",
    "securityDetail",
    "sessionControl",
    "internalData",
    "extensionServiceHeader"
})
public class ServiceHeader {

    @XmlElement(name = "StandardType")
    protected String standardType;
    @XmlElement(name = "StandardVersion")
    protected String standardVersion;
    @XmlElement(name = "TrackingID")
    protected String trackingID;
    @XmlElement(name = "ServiceName", required = true)
    protected String serviceName;
    @XmlElement(name = "ServiceVersion", required = true)
    protected String serviceVersion;
    @XmlElement(name = "SourceID")
    protected String sourceID;
    @XmlElement(name = "ChannelID")
    protected String channelID;
    @XmlElement(name = "TransactionID")
    protected String transactionID;
    @XmlElement(name = "CorrelationID")
    protected String correlationID;
    @XmlElement(name = "RqTimestamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rqTimestamp;
    @XmlElement(name = "RsTimestamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rsTimestamp;
    @XmlElement(name = "RqExpiration")
    protected Long rqExpiration;
    @XmlElement(name = "ServiceRouting")
    protected String serviceRouting;
    @XmlElement(name = "RetryCount")
    protected BigInteger retryCount;
    @XmlElement(name = "RetryInterval")
    protected Long retryInterval;
    @XmlElement(name = "StatusCode")
    protected BigInteger statusCode;
    @XmlElement(name = "SecurityDetail")
    protected SecurityDetail securityDetail;
    @XmlElement(name = "SessionControl")
    protected SessionControl sessionControl;
    @XmlElement(name = "InternalData")
    protected AnyContainer internalData;
    @XmlElement(name = "ExtensionServiceHeader")
    protected AnyContainer extensionServiceHeader;

    /**
     * 取得 standardType 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandardType() {
        return standardType;
    }

    /**
     * 設定 standardType 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandardType(String value) {
        this.standardType = value;
    }

    /**
     * 取得 standardVersion 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandardVersion() {
        return standardVersion;
    }

    /**
     * 設定 standardVersion 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandardVersion(String value) {
        this.standardVersion = value;
    }

    /**
     * 取得 trackingID 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrackingID() {
        return trackingID;
    }

    /**
     * 設定 trackingID 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrackingID(String value) {
        this.trackingID = value;
    }

    /**
     * 取得 serviceName 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * 設定 serviceName 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceName(String value) {
        this.serviceName = value;
    }

    /**
     * 取得 serviceVersion 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceVersion() {
        return serviceVersion;
    }

    /**
     * 設定 serviceVersion 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceVersion(String value) {
        this.serviceVersion = value;
    }

    /**
     * 取得 sourceID 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceID() {
        return sourceID;
    }

    /**
     * 設定 sourceID 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceID(String value) {
        this.sourceID = value;
    }

    /**
     * 取得 channelID 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelID() {
        return channelID;
    }

    /**
     * 設定 channelID 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelID(String value) {
        this.channelID = value;
    }

    /**
     * 取得 transactionID 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * 設定 transactionID 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
    }

    /**
     * 取得 correlationID 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelationID() {
        return correlationID;
    }

    /**
     * 設定 correlationID 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelationID(String value) {
        this.correlationID = value;
    }

    /**
     * 取得 rqTimestamp 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRqTimestamp() {
        return rqTimestamp;
    }

    /**
     * 設定 rqTimestamp 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRqTimestamp(XMLGregorianCalendar value) {
        this.rqTimestamp = value;
    }

    /**
     * 取得 rsTimestamp 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRsTimestamp() {
        return rsTimestamp;
    }

    /**
     * 設定 rsTimestamp 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRsTimestamp(XMLGregorianCalendar value) {
        this.rsTimestamp = value;
    }

    /**
     * 取得 rqExpiration 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRqExpiration() {
        return rqExpiration;
    }

    /**
     * 設定 rqExpiration 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRqExpiration(Long value) {
        this.rqExpiration = value;
    }

    /**
     * 取得 serviceRouting 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceRouting() {
        return serviceRouting;
    }

    /**
     * 設定 serviceRouting 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRouting(String value) {
        this.serviceRouting = value;
    }

    /**
     * 取得 retryCount 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRetryCount() {
        return retryCount;
    }

    /**
     * 設定 retryCount 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRetryCount(BigInteger value) {
        this.retryCount = value;
    }

    /**
     * 取得 retryInterval 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRetryInterval() {
        return retryInterval;
    }

    /**
     * 設定 retryInterval 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRetryInterval(Long value) {
        this.retryInterval = value;
    }

    /**
     * 取得 statusCode 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStatusCode() {
        return statusCode;
    }

    /**
     * 設定 statusCode 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStatusCode(BigInteger value) {
        this.statusCode = value;
    }

    /**
     * 取得 securityDetail 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link SecurityDetail }
     *     
     */
    public SecurityDetail getSecurityDetail() {
        return securityDetail;
    }

    /**
     * 設定 securityDetail 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityDetail }
     *     
     */
    public void setSecurityDetail(SecurityDetail value) {
        this.securityDetail = value;
    }

    /**
     * 取得 sessionControl 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link SessionControl }
     *     
     */
    public SessionControl getSessionControl() {
        return sessionControl;
    }

    /**
     * 設定 sessionControl 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionControl }
     *     
     */
    public void setSessionControl(SessionControl value) {
        this.sessionControl = value;
    }

    /**
     * 取得 internalData 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getInternalData() {
        return internalData;
    }

    /**
     * 設定 internalData 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setInternalData(AnyContainer value) {
        this.internalData = value;
    }

    /**
     * 取得 extensionServiceHeader 特性的值.
     * 
     * @return
     *     possible object is
     *     {@link AnyContainer }
     *     
     */
    public AnyContainer getExtensionServiceHeader() {
        return extensionServiceHeader;
    }

    /**
     * 設定 extensionServiceHeader 特性的值.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyContainer }
     *     
     */
    public void setExtensionServiceHeader(AnyContainer value) {
        this.extensionServiceHeader = value;
    }

}
