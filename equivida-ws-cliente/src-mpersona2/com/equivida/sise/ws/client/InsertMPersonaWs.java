/**
 * InsertMPersonaWs.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client;

public interface InsertMPersonaWs extends java.rmi.Remote {
    public com.equivida.sise.ws.client.RespuestaInsertMPersona ws_sise_insert_mpersona(java.lang.String txt_apellido1, java.lang.String txt_apellido2, java.lang.String txt_nombre, java.math.BigDecimal cod_tipo_doc, java.lang.String nro_doc, java.math.BigDecimal cod_tipo_iva, java.lang.String nro_nit, java.lang.String fec_nac, java.lang.String txt_lugar_nac, java.lang.String txt_sexo, java.math.BigDecimal cod_est_civil, java.lang.String cod_tipo_persona, java.lang.String txt_origen, java.lang.String txt_nombres_conyuge, java.lang.String txt_apellidos_conyuge, java.math.BigDecimal cod_tipo_doc_conyuge, java.lang.String nro_doc_conyugue, java.lang.String campo_in_1, java.lang.String campo_in_2, java.lang.String campo_in_3, java.lang.String campo_in_4, java.lang.String campo_in_5) throws java.rmi.RemoteException;
}
