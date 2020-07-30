package com.equivida.sise.ws.client;

public class InsertDireccionContratanteWsProxy implements com.equivida.sise.ws.client.InsertDireccionContratanteWs {
  private String _endpoint = null;
  private com.equivida.sise.ws.client.InsertDireccionContratanteWs insertDireccionContratanteWs = null;
  
  public InsertDireccionContratanteWsProxy() {
    _initInsertDireccionContratanteWsProxy();
  }
  
  public InsertDireccionContratanteWsProxy(String endpoint) {
    _endpoint = endpoint;
    _initInsertDireccionContratanteWsProxy();
  }
  
  private void _initInsertDireccionContratanteWsProxy() {
    try {
      insertDireccionContratanteWs = (new com.equivida.sise.ws.client.impl.InsertDireccionContratanteWsImplServiceLocator()).getInsertDireccionContratanteWsImplPort();
      if (insertDireccionContratanteWs != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)insertDireccionContratanteWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)insertDireccionContratanteWs)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (insertDireccionContratanteWs != null)
      ((javax.xml.rpc.Stub)insertDireccionContratanteWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.equivida.sise.ws.client.InsertDireccionContratanteWs getInsertDireccionContratanteWs() {
    if (insertDireccionContratanteWs == null)
      _initInsertDireccionContratanteWsProxy();
    return insertDireccionContratanteWs;
  }
  
  public com.equivida.sise.ws.client.InsertDireccionContratante spi_mpersona_dir_wkf(java.math.BigDecimal id_persona_wkf, java.math.BigDecimal cod_tipo_dir, java.lang.String txt_direccion, java.math.BigDecimal cod_municipio, java.math.BigDecimal cod_dpto, java.math.BigDecimal cod_pais) throws java.rmi.RemoteException{
    if (insertDireccionContratanteWs == null)
      _initInsertDireccionContratanteWsProxy();
    return insertDireccionContratanteWs.spi_mpersona_dir_wkf(id_persona_wkf, cod_tipo_dir, txt_direccion, cod_municipio, cod_dpto, cod_pais);
  }
  
  
}