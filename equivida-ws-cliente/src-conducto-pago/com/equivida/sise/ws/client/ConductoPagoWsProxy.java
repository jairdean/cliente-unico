package com.equivida.sise.ws.client;

public class ConductoPagoWsProxy implements com.equivida.sise.ws.client.ConductoPagoWs {
  private String _endpoint = null;
  private com.equivida.sise.ws.client.ConductoPagoWs conductoPagoWs = null;
  
  public ConductoPagoWsProxy() {
    _initConductoPagoWsProxy();
  }
  
  public ConductoPagoWsProxy(String endpoint) {
    _endpoint = endpoint;
    _initConductoPagoWsProxy();
  }
  
  private void _initConductoPagoWsProxy() {
    try {
      conductoPagoWs = (new com.equivida.sise.ws.client.impl.ConductaPagoWsImplServiceLocator()).getConductaPagoWsImplPort();
      if (conductoPagoWs != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)conductoPagoWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)conductoPagoWs)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (conductoPagoWs != null)
      ((javax.xml.rpc.Stub)conductoPagoWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.equivida.sise.ws.client.ConductoPagoWs getConductoPagoWs() {
    if (conductoPagoWs == null)
      _initConductoPagoWsProxy();
    return conductoPagoWs;
  }
  
  public com.equivida.sise.ws.client.RsConductoPago[] ws_sise_lista_conductos(java.math.BigDecimal id_persona) throws java.rmi.RemoteException{
    if (conductoPagoWs == null)
      _initConductoPagoWsProxy();
    return conductoPagoWs.ws_sise_lista_conductos(id_persona);
  }
  
  
}