package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsInfoPoliza;
import com.equivida.sise.sp.InfoPolizaSp;
import com.equivida.sise.ws.InfoPolizaWs;

@Stateless(name = "InfoPolizaWs")
@WebService(endpointInterface = "com.equivida.sise.ws.InfoPolizaWs")
@Remote(InfoPolizaWs.class)
public class InfoPolizaWsImpl implements InfoPolizaWs {

	@EJB
	private InfoPolizaSp infoPolizaSp;

	@Override
	@WebMethod
	public @WebResult(name = "IngresoCitas")
	List<RsInfoPoliza> ws_sise_info_poliza(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5) {

		List<RsInfoPoliza> respuesta = null;
		try {
			respuesta = infoPolizaSp.llamarInfoPolizaSp(cod_suc, cod_ramo,
					nro_pol, campoin1, campoin2, campoin3, campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}