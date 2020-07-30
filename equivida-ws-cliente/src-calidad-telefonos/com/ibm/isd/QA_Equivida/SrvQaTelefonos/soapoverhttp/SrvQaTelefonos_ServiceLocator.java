/**
 * SrvQaTelefonos_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp;

public class SrvQaTelefonos_ServiceLocator extends org.apache.axis.client.Service implements com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_Service {

    public SrvQaTelefonos_ServiceLocator() {
    }


    public SrvQaTelefonos_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvQaTelefonos_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvQaTelefonosSOAP
    private java.lang.String SrvQaTelefonosSOAP_address = "http://mamut:9080/wisd/QA_Equivida/SrvQaTelefonos";

    public java.lang.String getSrvQaTelefonosSOAPAddress() {
        return SrvQaTelefonosSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvQaTelefonosSOAPWSDDServiceName = "SrvQaTelefonosSOAP";

    public java.lang.String getSrvQaTelefonosSOAPWSDDServiceName() {
        return SrvQaTelefonosSOAPWSDDServiceName;
    }

    public void setSrvQaTelefonosSOAPWSDDServiceName(java.lang.String name) {
        SrvQaTelefonosSOAPWSDDServiceName = name;
    }

    public com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_PortType getSrvQaTelefonosSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvQaTelefonosSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvQaTelefonosSOAP(endpoint);
    }

    public com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_PortType getSrvQaTelefonosSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonosSOAPSoapBindingStub _stub = new com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonosSOAPSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvQaTelefonosSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvQaTelefonosSOAPEndpointAddress(java.lang.String address) {
        SrvQaTelefonosSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonosSOAPSoapBindingStub _stub = new com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonosSOAPSoapBindingStub(new java.net.URL(SrvQaTelefonosSOAP_address), this);
                _stub.setPortName(getSrvQaTelefonosSOAPWSDDServiceName());
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
        if ("SrvQaTelefonosSOAP".equals(inputPortName)) {
            return getSrvQaTelefonosSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://SrvQaTelefonos.QA_Equivida.isd.ibm.com/soapoverhttp/", "SrvQaTelefonos");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://SrvQaTelefonos.QA_Equivida.isd.ibm.com/soapoverhttp/", "SrvQaTelefonosSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvQaTelefonosSOAP".equals(portName)) {
            setSrvQaTelefonosSOAPEndpointAddress(address);
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
