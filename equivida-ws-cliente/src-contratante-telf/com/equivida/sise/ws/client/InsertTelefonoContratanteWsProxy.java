package com.equivida.sise.ws.client;

public class InsertTelefonoContratanteWsProxy implements com.equivida.sise.ws.client.InsertTelefonoContratanteWs {
  private String _endpoint = null;
  private com.equivida.sise.ws.client.InsertTelefonoContratanteWs insertTelefonoContratanteWs = null;
  
  public InsertTelefonoContratanteWsProxy() {
    _initInsertTelefonoContratanteWsProxy();
  }
  
  public InsertTelefonoContratanteWsProxy(String endpoint) {
    _endpoint = endpoint;
    _initInsertTelefonoContratanteWsProxy();
  }
  
  private void _initInsertTelefonoContratanteWsProxy() {
    try {
      insertTelefonoContratanteWs = (new com.equivida.sise.ws.client.impl.InsertTelefonoContratanteWsImplServiceLocator()).getInsertTelefonoContratanteWsImplPort();
      if (insertTelefonoContratanteWs != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)insertTelefonoContratanteWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)insertTelefonoContratanteWs)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (insertTelefonoContratanteWs != null)
      ((javax.xml.rpc.Stub)insertTelefonoContratanteWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.equivida.sise.ws.client.InsertTelefonoContratanteWs getInsertTelefonoContratanteWs() {
    if (insertTelefonoContratanteWs == null)
      _initInsertTelefonoContratanteWsProxy();
    return insertTelefonoContratanteWs;
  }
  
  public com.equivida.sise.ws.client.InsertTelefonoContratante spi_mpersona_telef_wkf(java.math.BigDecimal id_persona_wkf, java.math.BigDecimal cod_tipo_telef, java.lang.String txt_telefono) throws java.rmi.RemoteException{
    if (insertTelefonoContratanteWs == null)
      _initInsertTelefonoContratanteWsProxy();
    return insertTelefonoContratanteWs.spi_mpersona_telef_wkf(id_persona_wkf, cod_tipo_telef, txt_telefono);
  }
  
  
}