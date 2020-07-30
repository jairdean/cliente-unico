package com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp;

public class SrQaTelefonosProxy implements com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonos_PortType {
  private String _endpoint = null;
  private com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonos_PortType srQaTelefonos_PortType = null;
  
  public SrQaTelefonosProxy() {
    _initSrQaTelefonosProxy();
  }
  
  public SrQaTelefonosProxy(String endpoint) {
    _endpoint = endpoint;
    _initSrQaTelefonosProxy();
  }
  
  private void _initSrQaTelefonosProxy() {
    try {
      srQaTelefonos_PortType = (new com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonos_ServiceLocator()).getSrQaTelefonosSOAP();
      if (srQaTelefonos_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)srQaTelefonos_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)srQaTelefonos_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (srQaTelefonos_PortType != null)
      ((javax.xml.rpc.Stub)srQaTelefonos_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ibm.isd.QA_Equivida.SrQaTelefonos.soapoverhttp.SrQaTelefonos_PortType getSrQaTelefonos_PortType() {
    if (srQaTelefonos_PortType == null)
      _initSrQaTelefonosProxy();
    return srQaTelefonos_PortType;
  }
  
  public com.ibm.isd.QA_Equivida.SrQaTelefonos.SrQaTelefonosOutVar1 opQaTelefonos(java.lang.String te_numero) throws java.rmi.RemoteException{
    if (srQaTelefonos_PortType == null)
      _initSrQaTelefonosProxy();
    return srQaTelefonos_PortType.opQaTelefonos(te_numero);
  }
  
  
}