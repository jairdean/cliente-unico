package com.equivida.sise.ws.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsDatosAgente;
import com.equivida.sise.sp.DatosAgenteSp;
import com.equivida.sise.ws.DatosAgenteWs;

@Stateless(name = "DatosAgenteWs")
@WebService(endpointInterface = "com.equivida.sise.ws.DatosAgenteWs")
@Remote(DatosAgenteWs.class)
public class DatosAgenteWsImpl implements DatosAgenteWs {

	@EJB
	private DatosAgenteSp agenteSp;

	@Override
	@WebMethod
	public @WebResult(name = "DatosAgente")
	List<RsDatosAgente> ws_sise_datos_agente(String nro_doc, String campo1,
			String campo2, String campo3, String campo4, String campo5) {

		List<RsDatosAgente> respuestaLista = null;
		try {
			respuestaLista = agenteSp.llamarDatosAgenteSp(nro_doc, campo1,
					campo2, campo3, campo4, campo5);
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuestaLista;
	}
}