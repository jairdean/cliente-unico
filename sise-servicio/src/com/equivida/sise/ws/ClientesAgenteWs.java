package com.equivida.sise.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsClientesAgente;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ClientesAgenteWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsClientesAgente> ws_sise_clientes_agente(
			@WebParam(name = "numDoc") String numDoc,
			@WebParam(name = "tipoAgente") Integer tipoAgente,
			@WebParam(name = "codAgente") Integer codAgente,
			@WebParam(name = "snActivas") Integer snActivas,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);

}