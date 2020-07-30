package com.equivida.sise.ws.client;

public class InsertMPersonaWsProxy implements com.equivida.sise.ws.client.InsertMPersonaWs {
  private String _endpoint = null;
  private com.equivida.sise.ws.client.InsertMPersonaWs insertMPersonaWs = null;
  
  public InsertMPersonaWsProxy() {
    _initInsertMPersonaWsProxy();
  }
  
  public InsertMPersonaWsProxy(String endpoint) {
    _endpoint = endpoint;
    _initInsertMPersonaWsProxy();
  }
  
  private void _initInsertMPersonaWsProxy() {
    try {
      insertMPersonaWs = (new com.equivida.sise.ws.client.impl.InsertMPersonaWsImplServiceLocator()).getInsertMPersonaWsImplPort();
      if (insertMPersonaWs != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)insertMPersonaWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)insertMPersonaWs)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (insertMPersonaWs != null)
      ((javax.xml.rpc.Stub)insertMPersonaWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.equivida.sise.ws.client.InsertMPersonaWs getInsertMPersonaWs() {
    if (insertMPersonaWs == null)
      _initInsertMPersonaWsProxy();
    return insertMPersonaWs;
  }
  
  public com.equivida.sise.ws.client.RespuestaInsertMPersona ws_sise_insert_mpersona(java.lang.String txt_apellido1, java.lang.String txt_apellido2, java.lang.String txt_nombre, java.math.BigDecimal cod_tipo_doc, java.lang.String nro_doc, java.math.BigDecimal cod_tipo_iva, java.lang.String nro_nit, java.lang.String fec_nac, java.lang.String txt_lugar_nac, java.lang.String txt_sexo, java.math.BigDecimal cod_est_civil, java.lang.String cod_tipo_persona, java.lang.String txt_origen, java.lang.String txt_nombres_conyuge, java.lang.String txt_apellidos_conyuge, java.math.BigDecimal cod_tipo_doc_conyuge, java.lang.String nro_doc_conyugue, java.lang.String campo_in_1, java.lang.String campo_in_2, java.lang.String campo_in_3, java.lang.String campo_in_4, java.lang.String campo_in_5) throws java.rmi.RemoteException{
    if (insertMPersonaWs == null)
      _initInsertMPersonaWsProxy();
    return insertMPersonaWs.ws_sise_insert_mpersona(txt_apellido1, txt_apellido2, txt_nombre, cod_tipo_doc, nro_doc, cod_tipo_iva, nro_nit, fec_nac, txt_lugar_nac, txt_sexo, cod_est_civil, cod_tipo_persona, txt_origen, txt_nombres_conyuge, txt_apellidos_conyuge, cod_tipo_doc_conyuge, nro_doc_conyugue, campo_in_1, campo_in_2, campo_in_3, campo_in_4, campo_in_5);
  }
  
  
}