package com.equivida.sise.ws.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsCuotasPrestamo;
import com.equivida.sise.sp.CuotasPrestamoSp;
import com.equivida.sise.ws.CuotasPrestamoWs;

@Stateless(name = "CuotasPrestamoWs")
@WebService(endpointInterface = "com.equivida.sise.ws.CuotasPrestamoWs")
@Remote(CuotasPrestamoWs.class)
public class CuotasPrestamoWsImpl implements CuotasPrestamoWs {

	@EJB
	private CuotasPrestamoSp cuotasPrestamoSp;

	@Override
	@WebMethod
	public @WebResult(name = "CuotasPrestamo")
	ArrayList<RsCuotasPrestamo> ws_sise_cuotas_prestamo(Integer nro_perstamo,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) {

		ArrayList<RsCuotasPrestamo> respuesta = new ArrayList<RsCuotasPrestamo>();
		try {
			respuesta = cuotasPrestamoSp.llamarCuotasPrestamoSp(nro_perstamo,
					campoin1, campoin2, campoin3, campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}