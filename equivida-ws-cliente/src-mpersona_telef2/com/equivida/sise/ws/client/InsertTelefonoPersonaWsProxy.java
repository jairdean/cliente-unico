package com.equivida.sise.ws.client;

public class InsertTelefonoPersonaWsProxy implements com.equivida.sise.ws.client.InsertTelefonoPersonaWs {
  private String _endpoint = null;
  private com.equivida.sise.ws.client.InsertTelefonoPersonaWs insertTelefonoPersonaWs = null;
  
  public InsertTelefonoPersonaWsProxy() {
    _initInsertTelefonoPersonaWsProxy();
  }
  
  public InsertTelefonoPersonaWsProxy(String endpoint) {
    _endpoint = endpoint;
    _initInsertTelefonoPersonaWsProxy();
  }
  
  private void _initInsertTelefonoPersonaWsProxy() {
    try {
      insertTelefonoPersonaWs = (new com.equivida.sise.ws.client.impl.InsertTelefonoPersonaWsImplServiceLocator()).getInsertTelefonoPersonaWsImplPort();
      if (insertTelefonoPersonaWs != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)insertTelefonoPersonaWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)insertTelefonoPersonaWs)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (insertTelefonoPersonaWs != null)
      ((javax.xml.rpc.Stub)insertTelefonoPersonaWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.equivida.sise.ws.client.InsertTelefonoPersonaWs getInsertTelefonoPersonaWs() {
    if (insertTelefonoPersonaWs == null)
      _initInsertTelefonoPersonaWsProxy();
    return insertTelefonoPersonaWs;
  }
  
  public com.equivida.sise.ws.client.InsertTelefonoPersona ws_sise_insert_mpersona_telef(java.math.BigDecimal id_persona, java.math.BigDecimal cod_tipo_telef, java.lang.String txt_telefono, java.lang.String campoin1, java.lang.String campoin2, java.lang.String campoin3, java.lang.String campoin4, java.lang.String campoin5) throws java.rmi.RemoteException{
    if (insertTelefonoPersonaWs == null)
      _initInsertTelefonoPersonaWsProxy();
    return insertTelefonoPersonaWs.ws_sise_insert_mpersona_telef(id_persona, cod_tipo_telef, txt_telefono, campoin1, campoin2, campoin3, campoin4, campoin5);
  }
  
  
}