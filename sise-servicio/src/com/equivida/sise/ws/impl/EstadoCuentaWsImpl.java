package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsEstadoCuenta;
import com.equivida.sise.sp.EstadoCuentaSp;
import com.equivida.sise.ws.EstadoCuentaWs;

@Stateless(name = "EstadoCuentaWs")
@WebService(endpointInterface = "com.equivida.sise.ws.EstadoCuentaWs")
@Remote(EstadoCuentaWs.class)
public class EstadoCuentaWsImpl implements EstadoCuentaWs {

	@EJB
	private EstadoCuentaSp estadoCuentaSp;

	@Override
	@WebMethod
	public @WebResult(name = "EsatadoCuenta")
	List<RsEstadoCuenta> ws_sise_estado_cuenta(Integer cod_suc, Integer cod_ramo,
			BigDecimal nro_pol, String fec_desde, String fec_hasta,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) {

		List<RsEstadoCuenta> respuesta = new ArrayList<RsEstadoCuenta>();
		try {
			respuesta = estadoCuentaSp.llamarEstadoCuentaSp(cod_suc, cod_ramo,
					nro_pol, fec_desde, fec_hasta, campoin1, campoin2,
					campoin3, campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}