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

import com.equivida.sise.rs.RsInfoSaldosPolizas;
import com.equivida.sise.sp.InfoSaldosPolizasSp;
import com.equivida.sise.ws.InfoSaldosPolizasWs;

@Stateless(name = "InfoSaldosPolizasWs")
@WebService(endpointInterface = "com.equivida.sise.ws.InfoSaldosPolizasWs")
@Remote(InfoSaldosPolizasWs.class)
public class InfoSaldosPolizasWsImpl implements InfoSaldosPolizasWs {

	@EJB
	private InfoSaldosPolizasSp infoSaldosPolizasSp;

	@Override
	@WebMethod
	public @WebResult(name = "InfoSaldosPolizas")
	List<RsInfoSaldosPolizas> ws_sise_info_saldos_poliza(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5) {

		List<RsInfoSaldosPolizas> respuesta = new ArrayList<RsInfoSaldosPolizas>();
		try {
			respuesta = infoSaldosPolizasSp.llamarInfoSaldosPolizasSp(cod_suc,
					cod_ramo, nro_pol, campoin1, campoin2, campoin3, campoin4,
					campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}