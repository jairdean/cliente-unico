/**
 * ActualizacionesRegularesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class ActualizacionesRegularesLocator extends org.apache.axis.client.Service implements org.tempuri.ActualizacionesRegulares {

    public ActualizacionesRegularesLocator() {
    }


    public ActualizacionesRegularesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ActualizacionesRegularesLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ActualizacionesRegularesSoap
    private java.lang.String ActualizacionesRegularesSoap_address = "http://10.10.37.4:5555/ActualizacionesRegulares.asmx";

    public java.lang.String getActualizacionesRegularesSoapAddress() {
        return ActualizacionesRegularesSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ActualizacionesRegularesSoapWSDDServiceName = "ActualizacionesRegularesSoap";

    public java.lang.String getActualizacionesRegularesSoapWSDDServiceName() {
        return ActualizacionesRegularesSoapWSDDServiceName;
    }

    public void setActualizacionesRegularesSoapWSDDServiceName(java.lang.String name) {
        ActualizacionesRegularesSoapWSDDServiceName = name;
    }

    public org.tempuri.ActualizacionesRegularesSoap getActualizacionesRegularesSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ActualizacionesRegularesSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getActualizacionesRegularesSoap(endpoint);
    }

    public org.tempuri.ActualizacionesRegularesSoap getActualizacionesRegularesSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.ActualizacionesRegularesSoapStub _stub = new org.tempuri.ActualizacionesRegularesSoapStub(portAddress, this);
            _stub.setPortName(getActualizacionesRegularesSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setActualizacionesRegularesSoapEndpointAddress(java.lang.String address) {
        ActualizacionesRegularesSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.ActualizacionesRegularesSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.ActualizacionesRegularesSoapStub _stub = new org.tempuri.ActualizacionesRegularesSoapStub(new java.net.URL(ActualizacionesRegularesSoap_address), this);
                _stub.setPortName(getActualizacionesRegularesSoapWSDDServiceName());
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
        if ("ActualizacionesRegularesSoap".equals(inputPortName)) {
            return getActualizacionesRegularesSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ActualizacionesRegulares");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ActualizacionesRegularesSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ActualizacionesRegularesSoap".equals(portName)) {
            setActualizacionesRegularesSoapEndpointAddress(address);
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
