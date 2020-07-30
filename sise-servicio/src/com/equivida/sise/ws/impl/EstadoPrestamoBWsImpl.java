package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsEstadoPrestamo;
import com.equivida.sise.sp.EstadoPrestamoBSp;
import com.equivida.sise.ws.EstadoPrestamoBWs;

@Stateless(name = "EstadoPrestamoBWs")
@WebService(endpointInterface = "com.equivida.sise.ws.EstadoPrestamoBWs")
@Remote(EstadoPrestamoBWs.class)
public class EstadoPrestamoBWsImpl implements EstadoPrestamoBWs {

	@EJB
	private EstadoPrestamoBSp estadoPrestamoBSp;

	@Override
	@WebMethod
	public @WebResult(name = "EstadoPrestamo")
	ArrayList<RsEstadoPrestamo> ws_sise_estado_prestamo_B(Integer cod_suc,
			Integer cod_ramo, BigDecimal nro_pol, Integer sn_activos,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) {

		ArrayList<RsEstadoPrestamo> respuesta = new ArrayList<RsEstadoPrestamo>();
		try {
			respuesta = estadoPrestamoBSp.llamarEstadoPrestamoSp(cod_suc,
					cod_ramo, nro_pol, sn_activos, campoin1, campoin2,
					campoin3, campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}