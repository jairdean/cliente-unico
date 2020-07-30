package com.equivida.sise.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsComisionPagada;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ComisionPagadaWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsComisionPagada> ws_sise_comision_pagada(
			@WebParam(name = "cod_tipo_agente") Integer cod_tipo_agente,
			@WebParam(name = "cod_agente") Integer cod_agente,
			@WebParam(name = "fec_desde") String fec_desde,
			@WebParam(name = "fec_hasta") String fec_hasta,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);

}