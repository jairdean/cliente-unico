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

import com.equivida.sise.rs.RsAseguradosPoliza;
import com.equivida.sise.sp.AseguradosPolizaSp;
import com.equivida.sise.util.DateUtil;
import com.equivida.sise.ws.AseguradosPolizaWs;

@Stateless(name = "AseguradosPolizaWs")
@WebService(endpointInterface = "com.equivida.sise.ws.AseguradosPolizaWs")
@Remote(AseguradosPolizaWs.class)
public class AseguradosPolizaWsImpl implements AseguradosPolizaWs {

	@EJB
	private AseguradosPolizaSp aseguradosPolizaSp;

	@Override
	@WebMethod
	public @WebResult(name = "AseguradosPoliza")
	List<RsAseguradosPoliza> ws_sise_list_aseg_poliza(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, Integer sn_solo_activos,
			String fec_desde, String fec_hasta, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5) {

		List<RsAseguradosPoliza> respuesta = new ArrayList<RsAseguradosPoliza>();
		try {
			respuesta = aseguradosPolizaSp.llamarAseguradosPolizaSp(cod_suc,
					cod_ramo, nro_pol, sn_solo_activos,
					DateUtil.getFechaSL(fec_desde),
					DateUtil.getFechaSL(fec_hasta), campoin1, campoin2,
					campoin3, campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		} catch (ParseException e) {
			RsAseguradosPoliza aux = new RsAseguradosPoliza();
			aux.setCampo1("Formato de fecha incorrecto.");
			respuesta.add(aux);
		}

		return respuesta;
	}
}