//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.02 於 06:20:38 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ServiceHeader_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", "ServiceHeader");
    private final static QName _ExtensionSecurityDetail_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", "ExtensionSecurityDetail");
    private final static QName _ExtensionMessageIntegrity_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", "ExtensionMessageIntegrity");
    private final static QName _SecurityDetail_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", "SecurityDetail");
    private final static QName _ExtensionSecurityContext_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", "ExtensionSecurityContext");
    private final static QName _SecurityContext_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", "SecurityContext");
    private final static QName _MessageIntegrity_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", "MessageIntegrity");
    private final static QName _ExtensionSessionControl_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", "ExtensionSessionControl");
    private final static QName _SessionControl_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", "SessionControl");
    private final static QName _ExtensionServiceHeader_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", "ExtensionServiceHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceheader
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SecurityDetail }
     * 
     */
    public SecurityDetail createSecurityDetail() {
        return new SecurityDetail();
    }

    /**
     * Create an instance of {@link SessionControl }
     * 
     */
    public SessionControl createSessionControl() {
        return new SessionControl();
    }

    /**
     * Create an instance of {@link SecurityContext }
     * 
     */
    public SecurityContext createSecurityContext() {
        return new SecurityContext();
    }

    /**
     * Create an instance of {@link ServiceHeader }
     * 
     */
    public ServiceHeader createServiceHeader() {
        return new ServiceHeader();
    }

    /**
     * Create an instance of {@link MessageIntegrity }
     * 
     */
    public MessageIntegrity createMessageIntegrity() {
        return new MessageIntegrity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", name = "ServiceHeader")
    public JAXBElement<ServiceHeader> createServiceHeader(ServiceHeader value) {
        return new JAXBElement<ServiceHeader>(_ServiceHeader_QNAME, ServiceHeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", name = "ExtensionSecurityDetail")
    public JAXBElement<AnyContainer> createExtensionSecurityDetail(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtensionSecurityDetail_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", name = "ExtensionMessageIntegrity")
    public JAXBElement<AnyContainer> createExtensionMessageIntegrity(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtensionMessageIntegrity_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", name = "SecurityDetail")
    public JAXBElement<SecurityDetail> createSecurityDetail(SecurityDetail value) {
        return new JAXBElement<SecurityDetail>(_SecurityDetail_QNAME, SecurityDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", name = "ExtensionSecurityContext")
    public JAXBElement<AnyContainer> createExtensionSecurityContext(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtensionSecurityContext_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityContext }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", name = "SecurityContext")
    public JAXBElement<SecurityContext> createSecurityContext(SecurityContext value) {
        return new JAXBElement<SecurityContext>(_SecurityContext_QNAME, SecurityContext.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageIntegrity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", name = "MessageIntegrity")
    public JAXBElement<MessageIntegrity> createMessageIntegrity(MessageIntegrity value) {
        return new JAXBElement<MessageIntegrity>(_MessageIntegrity_QNAME, MessageIntegrity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", name = "ExtensionSessionControl")
    public JAXBElement<AnyContainer> createExtensionSessionControl(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtensionSessionControl_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SessionControl }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", name = "SessionControl")
    public JAXBElement<SessionControl> createSessionControl(SessionControl value) {
        return new JAXBElement<SessionControl>(_SessionControl_QNAME, SessionControl.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceHeader", name = "ExtensionServiceHeader")
    public JAXBElement<AnyContainer> createExtensionServiceHeader(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtensionServiceHeader_QNAME, AnyContainer.class, null, value);
    }

}
