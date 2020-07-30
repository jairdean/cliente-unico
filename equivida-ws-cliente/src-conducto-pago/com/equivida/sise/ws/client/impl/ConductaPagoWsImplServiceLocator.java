/**
 * ConductaPagoWsImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client.impl;

public class ConductaPagoWsImplServiceLocator extends org.apache.axis.client.Service implements com.equivida.sise.ws.client.impl.ConductaPagoWsImplService {

    public ConductaPagoWsImplServiceLocator() {
    }


    public ConductaPagoWsImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConductaPagoWsImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ConductaPagoWsImplPort
    private java.lang.String ConductaPagoWsImplPort_address = "http://localhost:8080/sise-servicio/ConductaPagoWsImpl";

    public java.lang.String getConductaPagoWsImplPortAddress() {
        return ConductaPagoWsImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ConductaPagoWsImplPortWSDDServiceName = "ConductaPagoWsImplPort";

    public java.lang.String getConductaPagoWsImplPortWSDDServiceName() {
        return ConductaPagoWsImplPortWSDDServiceName;
    }

    public void setConductaPagoWsImplPortWSDDServiceName(java.lang.String name) {
        ConductaPagoWsImplPortWSDDServiceName = name;
    }

    public com.equivida.sise.ws.client.ConductoPagoWs getConductaPagoWsImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ConductaPagoWsImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConductaPagoWsImplPort(endpoint);
    }

    public com.equivida.sise.ws.client.ConductoPagoWs getConductaPagoWsImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.equivida.sise.ws.client.impl.ConductaPagoWsImplServiceSoapBindingStub _stub = new com.equivida.sise.ws.client.impl.ConductaPagoWsImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getConductaPagoWsImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConductaPagoWsImplPortEndpointAddress(java.lang.String address) {
        ConductaPagoWsImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.equivida.sise.ws.client.ConductoPagoWs.class.isAssignableFrom(serviceEndpointInterface)) {
                com.equivida.sise.ws.client.impl.ConductaPagoWsImplServiceSoapBindingStub _stub = new com.equivida.sise.ws.client.impl.ConductaPagoWsImplServiceSoapBindingStub(new java.net.URL(ConductaPagoWsImplPort_address), this);
                _stub.setPortName(getConductaPagoWsImplPortWSDDServiceName());
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
        if ("ConductaPagoWsImplPort".equals(inputPortName)) {
            return getConductaPagoWsImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.ws.sise.equivida.com/", "ConductaPagoWsImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.ws.sise.equivida.com/", "ConductaPagoWsImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ConductaPagoWsImplPort".equals(portName)) {
            setConductaPagoWsImplPortEndpointAddress(address);
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
