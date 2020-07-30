/**
 * SrvQaEmail_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp;

public class SrvQaEmail_ServiceLocator extends org.apache.axis.client.Service implements com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmail_Service {

    public SrvQaEmail_ServiceLocator() {
    }


    public SrvQaEmail_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvQaEmail_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvQaEmailSOAP
    private java.lang.String SrvQaEmailSOAP_address = "http://10.10.30.65:9080/wisd/QA_Equivida/SrvQaEmail";

    public java.lang.String getSrvQaEmailSOAPAddress() {
        return SrvQaEmailSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvQaEmailSOAPWSDDServiceName = "SrvQaEmailSOAP";

    public java.lang.String getSrvQaEmailSOAPWSDDServiceName() {
        return SrvQaEmailSOAPWSDDServiceName;
    }

    public void setSrvQaEmailSOAPWSDDServiceName(java.lang.String name) {
        SrvQaEmailSOAPWSDDServiceName = name;
    }

    public com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmail_PortType getSrvQaEmailSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvQaEmailSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvQaEmailSOAP(endpoint);
    }

    public com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmail_PortType getSrvQaEmailSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmailSOAPSoapBindingStub _stub = new com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmailSOAPSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvQaEmailSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvQaEmailSOAPEndpointAddress(java.lang.String address) {
        SrvQaEmailSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmail_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmailSOAPSoapBindingStub _stub = new com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmailSOAPSoapBindingStub(new java.net.URL(SrvQaEmailSOAP_address), this);
                _stub.setPortName(getSrvQaEmailSOAPWSDDServiceName());
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
        if ("SrvQaEmailSOAP".equals(inputPortName)) {
            return getSrvQaEmailSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://SrQaEmail.QA_Equivida.isd.ibm.com/soapoverhttp/", "SrvQaEmail");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://SrQaEmail.QA_Equivida.isd.ibm.com/soapoverhttp/", "SrvQaEmailSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvQaEmailSOAP".equals(portName)) {
            setSrvQaEmailSOAPEndpointAddress(address);
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
