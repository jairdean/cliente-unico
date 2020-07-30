package com.equivida.sise.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsDatosAgente;

@WebService
@SOAPBinding(style = Style.RPC)
public interface DatosAgenteWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsDatosAgente> ws_sise_datos_agente(
			@WebParam(name = "numeroDoc") String nro_doc,
			@WebParam(name = "campo1") String campo1,
			@WebParam(name = "campo2") String campo2,
			@WebParam(name = "campo3") String campo3,
			@WebParam(name = "campo4") String campo4,
			@WebParam(name = "campo5") String campo5);
}