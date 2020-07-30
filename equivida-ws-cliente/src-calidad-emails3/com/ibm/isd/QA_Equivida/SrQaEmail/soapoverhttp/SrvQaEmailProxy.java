package com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp;

public class SrvQaEmailProxy implements com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmail_PortType {
  private String _endpoint = null;
  private com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmail_PortType srvQaEmail_PortType = null;
  
  public SrvQaEmailProxy() {
    _initSrvQaEmailProxy();
  }
  
  public SrvQaEmailProxy(String endpoint) {
    _endpoint = endpoint;
    _initSrvQaEmailProxy();
  }
  
  private void _initSrvQaEmailProxy() {
    try {
      srvQaEmail_PortType = (new com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmail_ServiceLocator()).getSrvQaEmailSOAP();
      if (srvQaEmail_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)srvQaEmail_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)srvQaEmail_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (srvQaEmail_PortType != null)
      ((javax.xml.rpc.Stub)srvQaEmail_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ibm.isd.QA_Equivida.SrQaEmail.soapoverhttp.SrvQaEmail_PortType getSrvQaEmail_PortType() {
    if (srvQaEmail_PortType == null)
      _initSrvQaEmailProxy();
    return srvQaEmail_PortType;
  }
  
  public com.ibm.isd.QA_Equivida.SrQaEmail.SrQaEmailOutVar1 srQaEmail(java.lang.String em_email) throws java.rmi.RemoteException{
    if (srvQaEmail_PortType == null)
      _initSrvQaEmailProxy();
    return srvQaEmail_PortType.srQaEmail(em_email);
  }
  
  
}