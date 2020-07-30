package com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp;

public class SrvQaTelefonosProxy implements com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_PortType {
  private String _endpoint = null;
  private com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_PortType srvQaTelefonos_PortType = null;
  
  public SrvQaTelefonosProxy() {
    _initSrvQaTelefonosProxy();
  }
  
  public SrvQaTelefonosProxy(String endpoint) {
    _endpoint = endpoint;
    _initSrvQaTelefonosProxy();
  }
  
  private void _initSrvQaTelefonosProxy() {
    try {
      srvQaTelefonos_PortType = (new com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_ServiceLocator()).getSrvQaTelefonosSOAP();
      if (srvQaTelefonos_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)srvQaTelefonos_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)srvQaTelefonos_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (srvQaTelefonos_PortType != null)
      ((javax.xml.rpc.Stub)srvQaTelefonos_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_PortType getSrvQaTelefonos_PortType() {
    if (srvQaTelefonos_PortType == null)
      _initSrvQaTelefonosProxy();
    return srvQaTelefonos_PortType;
  }
  
  public com.ibm.isd.QA_Equivida.SrvQaTelefonos.SrvQaTelefonosOutVar1 qaTelefonosSrv(java.lang.String te_numero) throws java.rmi.RemoteException{
    if (srvQaTelefonos_PortType == null)
      _initSrvQaTelefonosProxy();
    return srvQaTelefonos_PortType.qaTelefonosSrv(te_numero);
  }
  
  
}