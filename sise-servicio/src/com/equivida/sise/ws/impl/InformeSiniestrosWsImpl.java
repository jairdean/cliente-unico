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

import com.equivida.sise.rs.RsInformeSiniestros;
import com.equivida.sise.sp.InformeSiniestrosSp;
import com.equivida.sise.ws.InformeSiniestrosWs;

@Stateless(name = "InformeSiniestrosWs")
@WebService(endpointInterface = "com.equivida.sise.ws.InformeSiniestrosWs")
@Remote(InformeSiniestrosWs.class)
public class InformeSiniestrosWsImpl implements InformeSiniestrosWs {

	@EJB
	private InformeSiniestrosSp informeSiniestrosSp;

	@Override
	@WebMethod
	public @WebResult(name = "CoberturaPoliza")
	List<RsInformeSiniestros> ws_sise_info_siniestros(Integer cod_suc,
			Integer cod_ramo, Integer nro_stro, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5) {

		List<RsInformeSiniestros> respuesta = new ArrayList<RsInformeSiniestros>();
		try {
			respuesta = informeSiniestrosSp.llamarInformeSiniestrosSp(cod_suc,
					cod_ramo, nro_stro, campoin1, campoin2, campoin3, campoin4,
					campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}