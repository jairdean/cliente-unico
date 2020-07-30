/**
 * WsRCS_OFACLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package localhost;

public class WsRCS_OFACLocator extends org.apache.axis.client.Service implements localhost.WsRCS_OFAC {

    public WsRCS_OFACLocator() {
    }


    public WsRCS_OFACLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsRCS_OFACLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wsRCS_OFACSoap
    private java.lang.String wsRCS_OFACSoap_address = "http://10.10.30.22/RCS%20WS%20OFAC/service.asmx";

    public java.lang.String getwsRCS_OFACSoapAddress() {
        return wsRCS_OFACSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsRCS_OFACSoapWSDDServiceName = "wsRCS_OFACSoap";

    public java.lang.String getwsRCS_OFACSoapWSDDServiceName() {
        return wsRCS_OFACSoapWSDDServiceName;
    }

    public void setwsRCS_OFACSoapWSDDServiceName(java.lang.String name) {
        wsRCS_OFACSoapWSDDServiceName = name;
    }

    public localhost.WsRCS_OFACSoap getwsRCS_OFACSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsRCS_OFACSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwsRCS_OFACSoap(endpoint);
    }

    public localhost.WsRCS_OFACSoap getwsRCS_OFACSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            localhost.WsRCS_OFACSoapStub _stub = new localhost.WsRCS_OFACSoapStub(portAddress, this);
            _stub.setPortName(getwsRCS_OFACSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwsRCS_OFACSoapEndpointAddress(java.lang.String address) {
        wsRCS_OFACSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (localhost.WsRCS_OFACSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                localhost.WsRCS_OFACSoapStub _stub = new localhost.WsRCS_OFACSoapStub(new java.net.URL(wsRCS_OFACSoap_address), this);
                _stub.setPortName(getwsRCS_OFACSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("wsRCS_OFACSoap".equals(inputPortName)) {
            return getwsRCS_OFACSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://localhost/", "wsRCS_OFAC");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://localhost/", "wsRCS_OFACSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("wsRCS_OFACSoap".equals(portName)) {
            setwsRCS_OFACSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
