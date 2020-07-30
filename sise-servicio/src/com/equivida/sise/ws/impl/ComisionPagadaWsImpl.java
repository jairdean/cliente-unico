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

import com.equivida.sise.rs.RsComisionPagada;
import com.equivida.sise.sp.ComisionPagadaSp;
import com.equivida.sise.ws.ComisionPagadaWs;

@Stateless(name = "ComisionPagadaWs")
@WebService(endpointInterface = "com.equivida.sise.ws.ComisionPagadaWs")
@Remote(ComisionPagadaWs.class)
public class ComisionPagadaWsImpl implements ComisionPagadaWs {

	@EJB
	private ComisionPagadaSp comisionPagadaSp;

	@Override
	@WebMethod
	public @WebResult(name = "ComisionPagada")
	List<RsComisionPagada> ws_sise_comision_pagada(Integer cod_tipo_agente,
			Integer cod_agente, String fec_desde, String fec_hasta,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) {

		List<RsComisionPagada> respuesta = new ArrayList<RsComisionPagada>();
		try {
			respuesta = comisionPagadaSp.llamarComsionPagadaSp(cod_tipo_agente,
					cod_agente, fec_desde, fec_hasta, campoin1, campoin2,
					campoin3, campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}