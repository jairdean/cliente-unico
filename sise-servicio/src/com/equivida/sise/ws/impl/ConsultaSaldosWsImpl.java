package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsConsultaSaldos;
import com.equivida.sise.sp.ConsultaSaldosSp;
import com.equivida.sise.ws.ConsultaSaldosWs;
import com.equivida.sise.ws.RegistroClinicasWs;

@Stateless(name = "ConsultaSaldosWs")
@WebService(endpointInterface = "com.equivida.sise.ws.ConsultaSaldosWs")
@Remote(RegistroClinicasWs.class)
public class ConsultaSaldosWsImpl implements ConsultaSaldosWs {

	@EJB
	private ConsultaSaldosSp consultaSaldosSp;

	@Override
	@WebMethod
	public @WebResult(name = "ConsultaPersona")
	List<RsConsultaSaldos> WsConsultaSaldos(BigDecimal tipo_identificacion,
			String identificacion, BigDecimal tipo_identificacion_medico,
			String identificacion_medico, Integer sn_pagados) {

		List<RsConsultaSaldos> respuesta = null;
		try {
			respuesta = consultaSaldosSp.llamarConsultaSaldosSp(
					tipo_identificacion, identificacion,
					tipo_identificacion_medico, identificacion_medico,
					sn_pagados);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}