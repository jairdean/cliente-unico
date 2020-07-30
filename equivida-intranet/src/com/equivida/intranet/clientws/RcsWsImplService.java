
/*
 * 
 */

package com.equivida.intranet.clientws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.3.1-patch-01
 * Mon Apr 20 11:00:12 COT 2020
 * Generated source version: 2.3.1-patch-01
 * 
 */


@WebServiceClient(name = "RcsWsImplService", 
                  wsdlLocation = "http://10.10.43.4:8080/equivida-servicio-1.1.0/RcsWsImpl?wsdl",
                  targetNamespace = "http://impl.ws.equivida.com/") 
public class RcsWsImplService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://impl.ws.equivida.com/", "RcsWsImplService");
    public final static QName RcsWsImplPort = new QName("http://impl.ws.equivida.com/", "RcsWsImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.10.43.4:8080/equivida-servicio-1.1.0/RcsWsImpl?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://10.10.43.4:8080/equivida-servicio-1.1.0/RcsWsImpl?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public RcsWsImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public RcsWsImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RcsWsImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /*public RcsWsImplService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }
    public RcsWsImplService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public RcsWsImplService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }*/

    /**
     * 
     * @return
     *     returns RcsWs
     */
    @WebEndpoint(name = "RcsWsImplPort")
    public RcsWs getRcsWsImplPort() {
        return super.getPort(RcsWsImplPort, RcsWs.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RcsWs
     */
    @WebEndpoint(name = "RcsWsImplPort")
    public RcsWs getRcsWsImplPort(WebServiceFeature... features) {
        return super.getPort(RcsWsImplPort, RcsWs.class, features);
    }

}
