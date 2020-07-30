/**
 * SrQaTelefonos_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp;

public class SrQaTelefonos_ServiceLocator extends org.apache.axis.client.Service implements com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonos_Service {

    public SrQaTelefonos_ServiceLocator() {
    }


    public SrQaTelefonos_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrQaTelefonos_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrQaTelefonosSOAP
    private java.lang.String SrQaTelefonosSOAP_address = "http://10.10.30.31:9080/wisd/QA_Equivida/SrQaTelefonos";

    public java.lang.String getSrQaTelefonosSOAPAddress() {
        return SrQaTelefonosSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrQaTelefonosSOAPWSDDServiceName = "SrQaTelefonosSOAP";

    public java.lang.String getSrQaTelefonosSOAPWSDDServiceName() {
        return SrQaTelefonosSOAPWSDDServiceName;
    }

    public void setSrQaTelefonosSOAPWSDDServiceName(java.lang.String name) {
        SrQaTelefonosSOAPWSDDServiceName = name;
    }

    public com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonos_PortType getSrQaTelefonosSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrQaTelefonosSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrQaTelefonosSOAP(endpoint);
    }

    public com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonos_PortType getSrQaTelefonosSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonosSOAPSoapBindingStub _stub = new com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonosSOAPSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrQaTelefonosSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrQaTelefonosSOAPEndpointAddress(java.lang.String address) {
        SrQaTelefonosSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonos_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonosSOAPSoapBindingStub _stub = new com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonosSOAPSoapBindingStub(new java.net.URL(SrQaTelefonosSOAP_address), this);
                _stub.setPortName(getSrQaTelefonosSOAPWSDDServiceName());
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
        if ("SrQaTelefonosSOAP".equals(inputPortName)) {
            return getSrQaTelefonosSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://SrQaTelefonos.QA_Equivida.isd.ibm.com/soapoverhttp/", "SrQaTelefonos");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://SrQaTelefonos.QA_Equivida.isd.ibm.com/soapoverhttp/", "SrQaTelefonosSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrQaTelefonosSOAP".equals(portName)) {
            setSrQaTelefonosSOAPEndpointAddress(address);
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
