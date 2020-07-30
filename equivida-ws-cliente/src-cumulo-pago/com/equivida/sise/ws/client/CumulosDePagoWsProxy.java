package com.equivida.sise.ws.client;

public class CumulosDePagoWsProxy implements com.equivida.sise.ws.client.CumulosDePagoWs {
  private String _endpoint = null;
  private com.equivida.sise.ws.client.CumulosDePagoWs cumulosDePagoWs = null;
  
  public CumulosDePagoWsProxy() {
    _initCumulosDePagoWsProxy();
  }
  
  public CumulosDePagoWsProxy(String endpoint) {
    _endpoint = endpoint;
    _initCumulosDePagoWsProxy();
  }
  
  private void _initCumulosDePagoWsProxy() {
    try {
      cumulosDePagoWs = (new com.equivida.sise.ws.client.impl.CumulosDePagoWsImplServiceLocator()).getCumulosDePagoWsImplPort();
      if (cumulosDePagoWs != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cumulosDePagoWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cumulosDePagoWs)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cumulosDePagoWs != null)
      ((javax.xml.rpc.Stub)cumulosDePagoWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.equivida.sise.ws.client.CumulosDePagoWs getCumulosDePagoWs() {
    if (cumulosDePagoWs == null)
      _initCumulosDePagoWsProxy();
    return cumulosDePagoWs;
  }
  
  public com.equivida.sise.ws.client.RsCumulosDePago[] consultar(java.lang.String cedula) throws java.rmi.RemoteException{
    if (cumulosDePagoWs == null)
      _initCumulosDePagoWsProxy();
    return cumulosDePagoWs.consultar(cedula);
  }
  
  
}