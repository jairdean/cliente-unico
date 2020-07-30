package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsDisponibleRetiro;
import com.equivida.sise.sp.DisponibleRetiroSp;
import com.equivida.sise.util.DateUtil;
import com.equivida.sise.ws.DisponibleRetiroWs;

@Stateless(name = "DisponibleRetiroWs")
@WebService(endpointInterface = "com.equivida.sise.ws.DisponibleRetiroWs")
@Remote(DisponibleRetiroWs.class)
public class DisponibleRetiroWsImpl implements DisponibleRetiroWs {

	@EJB
	private DisponibleRetiroSp disponibleRetiroSp;

	@Override
	@WebMethod
	public @WebResult(name = "CoberturaPoliza")
	List<RsDisponibleRetiro> ws_sise_disponible_retiro(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, String fec_hasta,
			Integer sn_muestra, String campoin1, String campoin2,
			String campoin3, String campoin4, String campoin5) {

		List<RsDisponibleRetiro> respuesta = new ArrayList<RsDisponibleRetiro>();
		try {
			respuesta = disponibleRetiroSp.llamarDisponibleRetiroSp(cod_suc,
					cod_ramo, nro_pol, DateUtil.getFechaSL(fec_hasta),
					sn_muestra, campoin1, campoin2, campoin3, campoin4,
					campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		} catch (ParseException e) {
			RsDisponibleRetiro aux = new RsDisponibleRetiro();
			aux.setCampo1("Formato de fecha incorrecto.");
			respuesta.add(aux);
		}

		return respuesta;
	}
}