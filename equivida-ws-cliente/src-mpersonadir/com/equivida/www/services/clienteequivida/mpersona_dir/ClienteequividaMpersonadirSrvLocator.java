/**
 * ClienteequividaMpersonadirSrvLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.www.services.clienteequivida.mpersona_dir;

public class ClienteequividaMpersonadirSrvLocator extends org.apache.axis.client.Service implements com.equivida.www.services.clienteequivida.mpersona_dir.ClienteequividaMpersonadirSrv {

    public ClienteequividaMpersonadirSrvLocator() {
    }


    public ClienteequividaMpersonadirSrvLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ClienteequividaMpersonadirSrvLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Port
    private java.lang.String Port_address = "http://10.10.30.26:2014/process/clienteequivida.mpersonadir";

    public java.lang.String getPortAddress() {
        return Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PortWSDDServiceName = "Port";

    public java.lang.String getPortWSDDServiceName() {
        return PortWSDDServiceName;
    }

    public void setPortWSDDServiceName(java.lang.String name) {
        PortWSDDServiceName = name;
    }

    public com.equivida.www.services.clienteequivida.mpersona_dir.PortType getPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPort(endpoint);
    }

    public com.equivida.www.services.clienteequivida.mpersona_dir.PortType getPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.equivida.www.services.clienteequivida.mpersona_dir.SOAPBindingStub _stub = new com.equivida.www.services.clienteequivida.mpersona_dir.SOAPBindingStub(portAddress, this);
            _stub.setPortName(getPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPortEndpointAddress(java.lang.String address) {
        Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.equivida.www.services.clienteequivida.mpersona_dir.PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.equivida.www.services.clienteequivida.mpersona_dir.SOAPBindingStub _stub = new com.equivida.www.services.clienteequivida.mpersona_dir.SOAPBindingStub(new java.net.URL(Port_address), this);
                _stub.setPortName(getPortWSDDServiceName());
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
        if ("Port".equals(inputPortName)) {
            return getPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_dir", "clienteequivida.mpersonadirSrv");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.equivida.com/services/clienteequivida/mpersona_dir", "Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Port".equals(portName)) {
            setPortEndpointAddress(address);
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
