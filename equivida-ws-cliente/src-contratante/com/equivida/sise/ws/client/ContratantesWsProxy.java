package com.equivida.sise.ws.client;

public class ContratantesWsProxy implements com.equivida.sise.ws.client.ContratantesWs {
  private String _endpoint = null;
  private com.equivida.sise.ws.client.ContratantesWs contratantesWs = null;
  
  public ContratantesWsProxy() {
    _initContratantesWsProxy();
  }
  
  public ContratantesWsProxy(String endpoint) {
    _endpoint = endpoint;
    _initContratantesWsProxy();
  }
  
  private void _initContratantesWsProxy() {
    try {
      contratantesWs = (new com.equivida.sise.ws.client.impl.ContratanteWsImplServiceLocator()).getContratanteWsImplPort();
      if (contratantesWs != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)contratantesWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)contratantesWs)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (contratantesWs != null)
      ((javax.xml.rpc.Stub)contratantesWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.equivida.sise.ws.client.ContratantesWs getContratantesWs() {
    if (contratantesWs == null)
      _initContratantesWsProxy();
    return contratantesWs;
  }
  
  public com.equivida.sise.ws.client.RsContratante ws_sise_sp_contratante(com.equivida.sise.ws.client.ContratanteDto contratanteData) throws java.rmi.RemoteException{
    if (contratantesWs == null)
      _initContratantesWsProxy();
    return contratantesWs.ws_sise_sp_contratante(contratanteData);
  }
  
  
}