package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.InsertTelefonoPersona;

@WebService
@SOAPBinding(style = Style.RPC)
public interface InsertTelefonoPersonaWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	InsertTelefonoPersona ws_sise_insert_mpersona_telef(
			@WebParam(name = "id_persona") BigDecimal id_persona,
			@WebParam(name = "cod_tipo_telef") BigDecimal cod_tipo_telef,
			@WebParam(name = "txt_telefono") String txt_telefono,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);

}