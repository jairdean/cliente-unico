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

import com.equivida.sise.rs.RsCoberturaPolizas;
import com.equivida.sise.sp.CoberturaPolizaSp;
import com.equivida.sise.ws.CoberturaPolizaWs;

@Stateless(name = "CoberturaPolizaWs")
@WebService(endpointInterface = "com.equivida.sise.ws.CoberturaPolizaWs")
@Remote(CoberturaPolizaWs.class)
public class CoberturaPolizaWsImpl implements CoberturaPolizaWs {

	@EJB
	private CoberturaPolizaSp coberturaPolizaSp;

	@Override
	@WebMethod
	public @WebResult(name = "CoberturaPoliza")
	List<RsCoberturaPolizas> ws_sise_cobertura_poliza(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5) {

		List<RsCoberturaPolizas> respuesta = new ArrayList<RsCoberturaPolizas>();
		try {
			respuesta = coberturaPolizaSp.llamarCoberturaPolizaSp(cod_suc,
					cod_ramo, nro_pol, campoin1, campoin2, campoin3, campoin4,
					campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}