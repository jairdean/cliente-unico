package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.InsertDireccionPersona;

@WebService
@SOAPBinding(style = Style.RPC)
public interface InsertDireccionPersonaWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	InsertDireccionPersona ws_sise_insert_mpersonadir(
			@WebParam(name = "id_persona") BigDecimal id_persona,
			@WebParam(name = "cod_tipo_dir") BigDecimal cod_tipo_dir,
			@WebParam(name = "txt_direccion") String txt_direccion,
			@WebParam(name = "cod_municipio") BigDecimal cod_municipio,
			@WebParam(name = "cod_dpto") BigDecimal cod_dpto,
			@WebParam(name = "cod_pais") BigDecimal cod_pais,
			@WebParam(name = "txt_edificio") String txt_edificio,
			@WebParam(name = "txt_urbanizacion") String txt_urbanizacion,
			@WebParam(name = "txt_sector") String txt_sector,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);

}