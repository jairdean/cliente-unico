package com.equivida.sise.ws.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsComisionNoPagada;
import com.equivida.sise.sp.ComisionNoPagadaSp;
import com.equivida.sise.ws.ComisionNoPagadaWs;

@Stateless(name = "ComisionNoPagadaWs")
@WebService(endpointInterface = "com.equivida.sise.ws.ComisionNoPagadaWs")
@Remote(ComisionNoPagadaWs.class)
public class ComisionNoPagadaWsImpl implements ComisionNoPagadaWs {

	@EJB
	private ComisionNoPagadaSp comisionNoPagadaSp;

	@Override
	@WebMethod
	public @WebResult(name = "ComisionNoPagada")
	List<RsComisionNoPagada> ws_sise_comision_no_pagada(Integer cod_tipo_agente,
			Integer cod_agente, String fec_hasta, String fec_desde, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5) {

		List<RsComisionNoPagada> respuesta = new ArrayList<RsComisionNoPagada>();
		try {
			respuesta = comisionNoPagadaSp.llamarComisionNoPagadaSp(
					cod_tipo_agente, cod_agente, fec_hasta, fec_desde,campoin1, campoin2,
					campoin3, campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}