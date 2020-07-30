package com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp;

public class SrvQaNombresProxy implements com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombres_PortType {
  private String _endpoint = null;
  private com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombres_PortType srvQaNombres_PortType = null;
  
  public SrvQaNombresProxy() {
    _initSrvQaNombresProxy();
  }
  
  public SrvQaNombresProxy(String endpoint) {
    _endpoint = endpoint;
    _initSrvQaNombresProxy();
  }
  
  private void _initSrvQaNombresProxy() {
    try {
      srvQaNombres_PortType = (new com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombres_ServiceLocator()).getSrvQaNombresSOAP();
      if (srvQaNombres_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)srvQaNombres_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)srvQaNombres_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (srvQaNombres_PortType != null)
      ((javax.xml.rpc.Stub)srvQaNombres_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ibm.isd.QA_Equivida.SrQaNombres.soapoverhttp.SrvQaNombres_PortType getSrvQaNombres_PortType() {
    if (srvQaNombres_PortType == null)
      _initSrvQaNombresProxy();
    return srvQaNombres_PortType;
  }
  
  public com.ibm.isd.QA_Equivida.SrQaNombres.SrvQaNombresOutVar1 srQaNombres(java.lang.String txt_nombre) throws java.rmi.RemoteException{
    if (srvQaNombres_PortType == null)
      _initSrvQaNombresProxy();
    return srvQaNombres_PortType.srQaNombres(txt_nombre);
  }
  
  
}