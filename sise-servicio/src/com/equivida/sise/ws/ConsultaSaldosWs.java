package com.equivida.sise.ws;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsConsultaSaldos;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ConsultaSaldosWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsConsultaSaldos> WsConsultaSaldos(
			@WebParam(name = "tipo_identificacion") BigDecimal tipo_identificacion,
			@WebParam(name = "identificacion") String identificacion,
			@WebParam(name = "tipo_identificacion_medico") BigDecimal tipo_identificacion_medico,
			@WebParam(name = "identificacion_medico") String identificacion_medico,
			@WebParam(name = "sn_pagados") Integer sn_pagados);
}