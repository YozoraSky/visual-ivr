//
// 此檔案是由 JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 所產生 
// 請參閱 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2018.07.02 於 06:20:38 PM GMT+08:00 
//


package tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common package. 
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

    private final static QName _RqDetail_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "RqDetail");
    private final static QName _AnyContainer_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "AnyContainer");
    private final static QName _RsDetail_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "RsDetail");
    private final static QName _Cursor_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "Cursor");
    private final static QName _DataSet_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "DataSet");
    private final static QName _ExtensionCursor_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "ExtensionCursor");
    private final static QName _ExtensionError_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "ExtensionError");
    private final static QName _Data_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "Data");
    private final static QName _Error_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "Error");
    private final static QName _ExtensionRsDetail_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "ExtensionRsDetail");
    private final static QName _ExtentionDataSet_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "ExtentionDataSet");
    private final static QName _ExtensionRqDetail_QNAME = new QName("http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", "ExtensionRqDetail");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tw.com.chinatrust.ns.xsd.ctcb.esb.message.emf.common
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AnyContainer }
     * 
     */
    public AnyContainer createAnyContainer() {
        return new AnyContainer();
    }

    /**
     * Create an instance of {@link DataSet }
     * 
     */
    public DataSet createDataSet() {
        return new DataSet();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link Cursor }
     * 
     */
    public Cursor createCursor() {
        return new Cursor();
    }

    /**
     * Create an instance of {@link RsDetail }
     * 
     */
    public RsDetail createRsDetail() {
        return new RsDetail();
    }

    /**
     * Create an instance of {@link RqDetail }
     * 
     */
    public RqDetail createRqDetail() {
        return new RqDetail();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RqDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "RqDetail")
    public JAXBElement<RqDetail> createRqDetail(RqDetail value) {
        return new JAXBElement<RqDetail>(_RqDetail_QNAME, RqDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "AnyContainer")
    public JAXBElement<AnyContainer> createAnyContainer(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_AnyContainer_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RsDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "RsDetail")
    public JAXBElement<RsDetail> createRsDetail(RsDetail value) {
        return new JAXBElement<RsDetail>(_RsDetail_QNAME, RsDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cursor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "Cursor")
    public JAXBElement<Cursor> createCursor(Cursor value) {
        return new JAXBElement<Cursor>(_Cursor_QNAME, Cursor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "DataSet")
    public JAXBElement<DataSet> createDataSet(DataSet value) {
        return new JAXBElement<DataSet>(_DataSet_QNAME, DataSet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "ExtensionCursor")
    public JAXBElement<AnyContainer> createExtensionCursor(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtensionCursor_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "ExtensionError")
    public JAXBElement<AnyContainer> createExtensionError(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtensionError_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "Data")
    public JAXBElement<AnyContainer> createData(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_Data_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Error }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "Error")
    public JAXBElement<Error> createError(Error value) {
        return new JAXBElement<Error>(_Error_QNAME, Error.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "ExtensionRsDetail")
    public JAXBElement<AnyContainer> createExtensionRsDetail(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtensionRsDetail_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "ExtentionDataSet")
    public JAXBElement<AnyContainer> createExtentionDataSet(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtentionDataSet_QNAME, AnyContainer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AnyContainer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ns.chinatrust.com.tw/XSD/CTCB/ESB/Message/EMF/Common", name = "ExtensionRqDetail")
    public JAXBElement<AnyContainer> createExtensionRqDetail(AnyContainer value) {
        return new JAXBElement<AnyContainer>(_ExtensionRqDetail_QNAME, AnyContainer.class, null, value);
    }

}
