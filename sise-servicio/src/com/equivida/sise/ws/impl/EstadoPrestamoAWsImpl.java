package com.equivida.sise.ws.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsEstadoPrestamo;
import com.equivida.sise.sp.EstadoPrestamoSp;
import com.equivida.sise.ws.EstadoPrestamoAWs;

@Stateless(name = "EstadoPrestamoAWs")
@WebService(endpointInterface = "com.equivida.sise.ws.EstadoPrestamoAWs")
@Remote(EstadoPrestamoAWs.class)
public class EstadoPrestamoAWsImpl implements EstadoPrestamoAWs {

	@EJB
	private EstadoPrestamoSp estadoPrestamoSp;

	@Override
	@WebMethod
	public @WebResult(name = "EsatadoPrestamo")
	ArrayList<RsEstadoPrestamo> ws_sise_estado_prestamo_A(Integer cod_tipo_doc,
			String nro_doc, Integer sn_activos, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5) {

		ArrayList<RsEstadoPrestamo> respuesta = new ArrayList<RsEstadoPrestamo>();
		try {
			respuesta = estadoPrestamoSp.llamarEstadoPrestamoSp(cod_tipo_doc,
					nro_doc, sn_activos, campoin1, campoin2, campoin3,
					campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}