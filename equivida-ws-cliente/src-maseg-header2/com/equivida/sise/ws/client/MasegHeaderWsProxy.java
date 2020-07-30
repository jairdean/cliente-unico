package com.equivida.sise.ws.client;

public class MasegHeaderWsProxy implements com.equivida.sise.ws.client.MasegHeaderWs {
  private String _endpoint = null;
  private com.equivida.sise.ws.client.MasegHeaderWs masegHeaderWs = null;
  
  public MasegHeaderWsProxy() {
    _initMasegHeaderWsProxy();
  }
  
  public MasegHeaderWsProxy(String endpoint) {
    _endpoint = endpoint;
    _initMasegHeaderWsProxy();
  }
  
  private void _initMasegHeaderWsProxy() {
    try {
      masegHeaderWs = (new com.equivida.sise.ws.client.impl.MasegHeaderWsImplServiceLocator()).getMasegHeaderWsImplPort();
      if (masegHeaderWs != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)masegHeaderWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)masegHeaderWs)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (masegHeaderWs != null)
      ((javax.xml.rpc.Stub)masegHeaderWs)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.equivida.sise.ws.client.MasegHeaderWs getMasegHeaderWs() {
    if (masegHeaderWs == null)
      _initMasegHeaderWsProxy();
    return masegHeaderWs;
  }
  
  public com.equivida.sise.ws.client.MasegHeader ws_sise_insert_maseg_header_edm(java.math.BigDecimal id_persona, java.math.BigDecimal cod_figura_aseg, java.math.BigDecimal cod_tipo_aseg, java.math.BigDecimal cod_imp_aseg, java.math.BigDecimal cod_tipo_agente, java.math.BigDecimal cod_agente, java.lang.String fec_alta, java.lang.String fec_baja, java.math.BigDecimal cod_ocupacion, int sn_aviso_vto, java.lang.String cod_aseg_vinc, java.lang.String fec_ult_modif, java.lang.String cod_usuario, java.lang.String txt_nom_factura, java.math.BigDecimal pje_tasa_fianzas, int sn_consorcio, java.math.BigDecimal cod_moneda, java.math.BigDecimal imp_sueldo, java.math.BigDecimal cod_deporte, java.lang.String txt_edificio, java.lang.String txt_urbanizacion, java.lang.String txt_sector, java.lang.String txt_nombres_conyugue, java.lang.String txt_apellido_conyugue, java.math.BigDecimal cod_tipo_doc_conyugue, java.lang.String nro_doc_conyugue, java.lang.String campoin1, java.lang.String campoin2, java.lang.String campoin3, java.lang.String campoin4, java.lang.String campoin5) throws java.rmi.RemoteException{
    if (masegHeaderWs == null)
      _initMasegHeaderWsProxy();
    return masegHeaderWs.ws_sise_insert_maseg_header_edm(id_persona, cod_figura_aseg, cod_tipo_aseg, cod_imp_aseg, cod_tipo_agente, cod_agente, fec_alta, fec_baja, cod_ocupacion, sn_aviso_vto, cod_aseg_vinc, fec_ult_modif, cod_usuario, txt_nom_factura, pje_tasa_fianzas, sn_consorcio, cod_moneda, imp_sueldo, cod_deporte, txt_edificio, txt_urbanizacion, txt_sector, txt_nombres_conyugue, txt_apellido_conyugue, cod_tipo_doc_conyugue, nro_doc_conyugue, campoin1, campoin2, campoin3, campoin4, campoin5);
  }
  
  
}