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

import com.equivida.sise.rs.RsConsultaPolizas;
import com.equivida.sise.sp.ConsultaPolizasSp;
import com.equivida.sise.ws.ConsultaPolizasWs;

@Stateless(name = "ConsultaPolizasWs")
@WebService(endpointInterface = "com.equivida.sise.ws.ConsultaPolizasWs")
@Remote(ConsultaPolizasWs.class)
public class ConsultaPolizasWsImpl implements ConsultaPolizasWs {

	@EJB
	private ConsultaPolizasSp consultaPolizasSp;

	@Override
	@WebMethod
	public @WebResult(name = "ConsultaPolizas")
	List<RsConsultaPolizas> llamarConsultaPolizasSp(String nro_doc,
			BigDecimal id_persona, Integer sn_activas, Integer tipo_busca,
			String campo1, String campo2, String campo3, String campo4,
			String campo5) {
		List<RsConsultaPolizas> respuesta = null;

		try {
			respuesta = consultaPolizasSp.llamarConsultaPolizasSp(id_persona,
					nro_doc, sn_activas, tipo_busca, campo1, campo2, campo3,
					campo4, campo5);
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}