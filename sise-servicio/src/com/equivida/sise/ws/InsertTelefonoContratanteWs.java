package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.InsertTelefonoContratante;

@WebService
@SOAPBinding(style = Style.RPC)
public interface InsertTelefonoContratanteWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	InsertTelefonoContratante spi_mpersona_telef_wkf(@WebParam(name = "id_persona_wkf") BigDecimal id_persona_wkf,
			@WebParam(name = "cod_tipo_telef") BigDecimal cod_tipo_telef,
			@WebParam(name = "txt_telefono") String txt_telefono);

}