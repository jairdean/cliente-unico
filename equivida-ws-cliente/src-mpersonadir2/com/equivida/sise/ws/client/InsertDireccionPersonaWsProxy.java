package com.equivida.sise.ws.client;

public class InsertDireccionPersonaWsProxy implements com.equivida.sise.ws.client.InsertDireccionPersonaWs {
  private String _endpoint = null;
  private com.equivida.sise.ws.client.InsertDireccionPersonaWs insertDireccionPersonaWs = null;
  
  public InsertDireccionPersonaWsProxy() {
    _initInsertDireccionPersonaWsProxy();
  }
  
  public InsertDireccionPersonaWsProxy(String endpoint) {
    _endpoint = endpoint;
    _initInsertDireccionPersonaWsProxy();
  }
  
  private void _initInsertDireccionPersonaWsProxy() {
    try {
      insertDireccionPersonaWs = (new com.equivida.sise.ws.client.impl.InsertDireccionPersonaWsImplServiceLocator()).getInsertDireccionPersonaWsImplPort();
      if (insertDireccionPersonaWs != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)insertDireccionPersonaWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)insertDireccionPersonaWs)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (insertDireccionPersonaWs != null)
      ((javax.xml.rpc.Stub)insertDireccionPersonaWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.equivida.sise.ws.client.InsertDireccionPersonaWs getInsertDireccionPersonaWs() {
    if (insertDireccionPersonaWs == null)
      _initInsertDireccionPersonaWsProxy();
    return insertDireccionPersonaWs;
  }
  
  public com.equivida.sise.ws.client.InsertDireccionPersona ws_sise_insert_mpersonadir(java.math.BigDecimal id_persona, java.math.BigDecimal cod_tipo_dir, java.lang.String txt_direccion, java.math.BigDecimal cod_municipio, java.math.BigDecimal cod_dpto, java.math.BigDecimal cod_pais, java.lang.String txt_edificio, java.lang.String txt_urbanizacion, java.lang.String txt_sector, java.lang.String campoin1, java.lang.String campoin2, java.lang.String campoin3, java.lang.String campoin4, java.lang.String campoin5) throws java.rmi.RemoteException{
    if (insertDireccionPersonaWs == null)
      _initInsertDireccionPersonaWsProxy();
    return insertDireccionPersonaWs.ws_sise_insert_mpersonadir(id_persona, cod_tipo_dir, txt_direccion, cod_municipio, cod_dpto, cod_pais, txt_edificio, txt_urbanizacion, txt_sector, campoin1, campoin2, campoin3, campoin4, campoin5);
  }
  
  
}