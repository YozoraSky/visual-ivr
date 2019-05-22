//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.02 於 06:20:38 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common.AnyContainer;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope package. 
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

    private final static QName _ExtensionServiceEnvelope_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceEnvelope", "ExtensionServiceEnvelope");
    private final static QName _ServiceEnvelope_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceEnvelope", "ServiceEnvelope");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.serviceenvelope
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ServiceEnvelope }
     * 
     */
    public ServiceEnvelope createServiceEnvelope() {
        return new ServiceEnvelope();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceEnvelope", name = "ExtensionServiceEnvelope")
    public JAXBElement<AnyContainer> createExtensionServiceEnvelope(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtensionServiceEnvelope_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceEnvelope }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/ServiceEnvelope", name = "ServiceEnvelope")
    public JAXBElement<ServiceEnvelope> createServiceEnvelope(ServiceEnvelope value) {
        return new JAXBElement<ServiceEnvelope>(_ServiceEnvelope_QNAME, ServiceEnvelope.class, null, value);
    }

}
