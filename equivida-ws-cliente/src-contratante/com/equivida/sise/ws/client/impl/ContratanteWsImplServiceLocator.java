/**
 * ContratanteWsImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client.impl;

public class ContratanteWsImplServiceLocator extends org.apache.axis.client.Service implements com.equivida.sise.ws.client.impl.ContratanteWsImplService {

    public ContratanteWsImplServiceLocator() {
    }


    public ContratanteWsImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ContratanteWsImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ContratanteWsImplPort
    private java.lang.String ContratanteWsImplPort_address = "http://200.32.69.186:5051/sise-servicio-1.0/ContratanteWsImpl";

    public java.lang.String getContratanteWsImplPortAddress() {
        return ContratanteWsImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ContratanteWsImplPortWSDDServiceName = "ContratanteWsImplPort";

    public java.lang.String getContratanteWsImplPortWSDDServiceName() {
        return ContratanteWsImplPortWSDDServiceName;
    }

    public void setContratanteWsImplPortWSDDServiceName(java.lang.String name) {
        ContratanteWsImplPortWSDDServiceName = name;
    }

    public com.equivida.sise.ws.client.ContratantesWs getContratanteWsImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ContratanteWsImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getContratanteWsImplPort(endpoint);
    }

    public com.equivida.sise.ws.client.ContratantesWs getContratanteWsImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.equivida.sise.ws.client.impl.ContratanteWsImplServiceSoapBindingStub _stub = new com.equivida.sise.ws.client.impl.ContratanteWsImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getContratanteWsImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setContratanteWsImplPortEndpointAddress(java.lang.String address) {
        ContratanteWsImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.equivida.sise.ws.client.ContratantesWs.class.isAssignableFrom(serviceEndpointInterface)) {
                com.equivida.sise.ws.client.impl.ContratanteWsImplServiceSoapBindingStub _stub = new com.equivida.sise.ws.client.impl.ContratanteWsImplServiceSoapBindingStub(new java.net.URL(ContratanteWsImplPort_address), this);
                _stub.setPortName(getContratanteWsImplPortWSDDServiceName());
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
        if ("ContratanteWsImplPort".equals(inputPortName)) {
            return getContratanteWsImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.ws.sise.equivida.com/", "ContratanteWsImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.ws.sise.equivida.com/", "ContratanteWsImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ContratanteWsImplPort".equals(portName)) {
            setContratanteWsImplPortEndpointAddress(address);
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
