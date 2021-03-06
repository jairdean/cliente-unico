/**
 * SrvQaNombres_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp;

public class SrvQaNombres_ServiceLocator extends org.apache.axis.client.Service implements com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombres_Service {

    public SrvQaNombres_ServiceLocator() {
    }


    public SrvQaNombres_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SrvQaNombres_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SrvQaNombresSOAP
    private java.lang.String SrvQaNombresSOAP_address = "http://10.10.30.65:9080/wisd/QA_Equivida/SrvQaNombres";

    public java.lang.String getSrvQaNombresSOAPAddress() {
        return SrvQaNombresSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SrvQaNombresSOAPWSDDServiceName = "SrvQaNombresSOAP";

    public java.lang.String getSrvQaNombresSOAPWSDDServiceName() {
        return SrvQaNombresSOAPWSDDServiceName;
    }

    public void setSrvQaNombresSOAPWSDDServiceName(java.lang.String name) {
        SrvQaNombresSOAPWSDDServiceName = name;
    }

    public com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombres_PortType getSrvQaNombresSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SrvQaNombresSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSrvQaNombresSOAP(endpoint);
    }

    public com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombres_PortType getSrvQaNombresSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombresSOAPSoapBindingStub _stub = new com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombresSOAPSoapBindingStub(portAddress, this);
            _stub.setPortName(getSrvQaNombresSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSrvQaNombresSOAPEndpointAddress(java.lang.String address) {
        SrvQaNombresSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombres_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombresSOAPSoapBindingStub _stub = new com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombresSOAPSoapBindingStub(new java.net.URL(SrvQaNombresSOAP_address), this);
                _stub.setPortName(getSrvQaNombresSOAPWSDDServiceName());
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
        if ("SrvQaNombresSOAP".equals(inputPortName)) {
            return getSrvQaNombresSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://SrQaNombres.QA_Equivida.isd.ibm.com/soapoverhttp/", "SrvQaNombres");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://SrQaNombres.QA_Equivida.isd.ibm.com/soapoverhttp/", "SrvQaNombresSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SrvQaNombresSOAP".equals(portName)) {
            setSrvQaNombresSOAPEndpointAddress(address);
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
