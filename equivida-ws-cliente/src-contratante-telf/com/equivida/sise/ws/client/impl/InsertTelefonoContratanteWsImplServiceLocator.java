/**
 * InsertTelefonoContratanteWsImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client.impl;

public class InsertTelefonoContratanteWsImplServiceLocator extends org.apache.axis.client.Service implements com.equivida.sise.ws.client.impl.InsertTelefonoContratanteWsImplService {

    public InsertTelefonoContratanteWsImplServiceLocator() {
    }


    public InsertTelefonoContratanteWsImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InsertTelefonoContratanteWsImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InsertTelefonoContratanteWsImplPort
    private java.lang.String InsertTelefonoContratanteWsImplPort_address = "http://200.32.69.186:5051/sise-servicio-1.0/InsertTelefonoContratanteWsImpl";

    public java.lang.String getInsertTelefonoContratanteWsImplPortAddress() {
        return InsertTelefonoContratanteWsImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InsertTelefonoContratanteWsImplPortWSDDServiceName = "InsertTelefonoContratanteWsImplPort";

    public java.lang.String getInsertTelefonoContratanteWsImplPortWSDDServiceName() {
        return InsertTelefonoContratanteWsImplPortWSDDServiceName;
    }

    public void setInsertTelefonoContratanteWsImplPortWSDDServiceName(java.lang.String name) {
        InsertTelefonoContratanteWsImplPortWSDDServiceName = name;
    }

    public com.equivida.sise.ws.client.InsertTelefonoContratanteWs getInsertTelefonoContratanteWsImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InsertTelefonoContratanteWsImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInsertTelefonoContratanteWsImplPort(endpoint);
    }

    public com.equivida.sise.ws.client.InsertTelefonoContratanteWs getInsertTelefonoContratanteWsImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.equivida.sise.ws.client.impl.InsertTelefonoContratanteWsImplServiceSoapBindingStub _stub = new com.equivida.sise.ws.client.impl.InsertTelefonoContratanteWsImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getInsertTelefonoContratanteWsImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInsertTelefonoContratanteWsImplPortEndpointAddress(java.lang.String address) {
        InsertTelefonoContratanteWsImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.equivida.sise.ws.client.InsertTelefonoContratanteWs.class.isAssignableFrom(serviceEndpointInterface)) {
                com.equivida.sise.ws.client.impl.InsertTelefonoContratanteWsImplServiceSoapBindingStub _stub = new com.equivida.sise.ws.client.impl.InsertTelefonoContratanteWsImplServiceSoapBindingStub(new java.net.URL(InsertTelefonoContratanteWsImplPort_address), this);
                _stub.setPortName(getInsertTelefonoContratanteWsImplPortWSDDServiceName());
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
        if ("InsertTelefonoContratanteWsImplPort".equals(inputPortName)) {
            return getInsertTelefonoContratanteWsImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.ws.sise.equivida.com/", "InsertTelefonoContratanteWsImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.ws.sise.equivida.com/", "InsertTelefonoContratanteWsImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InsertTelefonoContratanteWsImplPort".equals(portName)) {
            setInsertTelefonoContratanteWsImplPortEndpointAddress(address);
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
