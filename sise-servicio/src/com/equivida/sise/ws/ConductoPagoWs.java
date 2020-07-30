package com.equivida.sise.ws;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsConductoPago;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ConductoPagoWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsConductoPago> ws_sise_lista_conductos(
			@WebParam(name = "id_persona") BigDecimal id_persona);

}