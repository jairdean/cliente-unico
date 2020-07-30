/**
 * InsertDireccionPersonaWs.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.equivida.sise.ws.client;

public interface InsertDireccionPersonaWs extends java.rmi.Remote {
    public com.equivida.sise.ws.client.InsertDireccionPersona ws_sise_insert_mpersonadir(java.math.BigDecimal id_persona, java.math.BigDecimal cod_tipo_dir, java.lang.String txt_direccion, java.math.BigDecimal cod_municipio, java.math.BigDecimal cod_dpto, java.math.BigDecimal cod_pais, java.lang.String txt_edificio, java.lang.String txt_urbanizacion, java.lang.String txt_sector, java.lang.String campoin1, java.lang.String campoin2, java.lang.String campoin3, java.lang.String campoin4, java.lang.String campoin5) throws java.rmi.RemoteException;
}
