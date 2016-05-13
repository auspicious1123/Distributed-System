
package cmu.edu.andrew.rw1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cmu.edu.andrew.rw1 package. 
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

    private final static QName _GetResponse_QNAME = new QName("http://rw1.andrew.edu.cmu/", "getResponse");
    private final static QName _Get_QNAME = new QName("http://rw1.andrew.edu.cmu/", "get");
    private final static QName _Bump_QNAME = new QName("http://rw1.andrew.edu.cmu/", "bump");
    private final static QName _BumpResponse_QNAME = new QName("http://rw1.andrew.edu.cmu/", "bumpResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cmu.edu.andrew.rw1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetResponse }
     * 
     */
    public GetResponse createGetResponse() {
        return new GetResponse();
    }

    /**
     * Create an instance of {@link Get }
     * 
     */
    public Get createGet() {
        return new Get();
    }

    /**
     * Create an instance of {@link Bump_Type }
     * 
     */
    public Bump_Type createBump_Type() {
        return new Bump_Type();
    }

    /**
     * Create an instance of {@link BumpResponse }
     * 
     */
    public BumpResponse createBumpResponse() {
        return new BumpResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rw1.andrew.edu.cmu/", name = "getResponse")
    public JAXBElement<GetResponse> createGetResponse(GetResponse value) {
        return new JAXBElement<GetResponse>(_GetResponse_QNAME, GetResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Get }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rw1.andrew.edu.cmu/", name = "get")
    public JAXBElement<Get> createGet(Get value) {
        return new JAXBElement<Get>(_Get_QNAME, Get.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Bump_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rw1.andrew.edu.cmu/", name = "bump")
    public JAXBElement<Bump_Type> createBump(Bump_Type value) {
        return new JAXBElement<Bump_Type>(_Bump_QNAME, Bump_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BumpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rw1.andrew.edu.cmu/", name = "bumpResponse")
    public JAXBElement<BumpResponse> createBumpResponse(BumpResponse value) {
        return new JAXBElement<BumpResponse>(_BumpResponse_QNAME, BumpResponse.class, null, value);
    }

}
