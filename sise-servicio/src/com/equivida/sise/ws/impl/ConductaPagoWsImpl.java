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

import com.equivida.sise.rs.RsConductoPago;
import com.equivida.sise.sp.ConductoPagoSp;
import com.equivida.sise.ws.ConductoPagoWs;

@Stateless(name = "ConductoPagoWs")
@WebService(endpointInterface = "com.equivida.sise.ws.ConductoPagoWs")
@Remote(ConductoPagoWs.class)
public class ConductaPagoWsImpl implements ConductoPagoWs {

	@EJB
	private ConductoPagoSp conductoPagoSp;

	@Override
	@WebMethod
	public @WebResult(name = "ConductoPagos")
	List<RsConductoPago> ws_sise_lista_conductos(BigDecimal id_persona) {

		List<RsConductoPago> respuestaLista = null;
		try {
			respuestaLista = conductoPagoSp.llamarConductoPagoSp(id_persona);
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuestaLista;
	}
}