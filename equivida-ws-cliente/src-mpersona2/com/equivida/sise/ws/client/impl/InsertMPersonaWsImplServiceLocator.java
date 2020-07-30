/**
 * InsertMPersonaWsImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client.impl;

public class InsertMPersonaWsImplServiceLocator extends org.apache.axis.client.Service implements com.equivida.sise.ws.client.impl.InsertMPersonaWsImplService {

    public InsertMPersonaWsImplServiceLocator() {
    }


    public InsertMPersonaWsImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InsertMPersonaWsImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InsertMPersonaWsImplPort
    private java.lang.String InsertMPersonaWsImplPort_address = "http://localhost:8080/sise-servicio/InsertMPersonaWsImpl";

    public java.lang.String getInsertMPersonaWsImplPortAddress() {
        return InsertMPersonaWsImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InsertMPersonaWsImplPortWSDDServiceName = "InsertMPersonaWsImplPort";

    public java.lang.String getInsertMPersonaWsImplPortWSDDServiceName() {
        return InsertMPersonaWsImplPortWSDDServiceName;
    }

    public void setInsertMPersonaWsImplPortWSDDServiceName(java.lang.String name) {
        InsertMPersonaWsImplPortWSDDServiceName = name;
    }

    public com.equivida.sise.ws.client.InsertMPersonaWs getInsertMPersonaWsImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InsertMPersonaWsImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInsertMPersonaWsImplPort(endpoint);
    }

    public com.equivida.sise.ws.client.InsertMPersonaWs getInsertMPersonaWsImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.equivida.sise.ws.client.impl.InsertMPersonaWsImplServiceSoapBindingStub _stub = new com.equivida.sise.ws.client.impl.InsertMPersonaWsImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getInsertMPersonaWsImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInsertMPersonaWsImplPortEndpointAddress(java.lang.String address) {
        InsertMPersonaWsImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.equivida.sise.ws.client.InsertMPersonaWs.class.isAssignableFrom(serviceEndpointInterface)) {
                com.equivida.sise.ws.client.impl.InsertMPersonaWsImplServiceSoapBindingStub _stub = new com.equivida.sise.ws.client.impl.InsertMPersonaWsImplServiceSoapBindingStub(new java.net.URL(InsertMPersonaWsImplPort_address), this);
                _stub.setPortName(getInsertMPersonaWsImplPortWSDDServiceName());
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
        if ("InsertMPersonaWsImplPort".equals(inputPortName)) {
            return getInsertMPersonaWsImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.ws.sise.equivida.com/", "InsertMPersonaWsImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.ws.sise.equivida.com/", "InsertMPersonaWsImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InsertMPersonaWsImplPort".equals(portName)) {
            setInsertMPersonaWsImplPortEndpointAddress(address);
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
