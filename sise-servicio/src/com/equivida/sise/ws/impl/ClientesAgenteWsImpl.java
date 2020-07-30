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

import com.equivida.sise.rs.RsClientesAgente;
import com.equivida.sise.sp.ClientesAgenteSp;
import com.equivida.sise.ws.ClientesAgenteWs;

@Stateless(name = "ClientesAgenteWs")
@WebService(endpointInterface = "com.equivida.sise.ws.ClientesAgenteWs")
@Remote(ClientesAgenteWs.class)
public class ClientesAgenteWsImpl implements ClientesAgenteWs {

	@EJB
	private ClientesAgenteSp agenteSp;

	@Override
	@WebMethod
	public @WebResult(name = "ClientesAgente")
	List<RsClientesAgente> ws_sise_clientes_agente(String numDoc,
			Integer tipoAgente, Integer codAgente, Integer snActivas,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) {

		List<RsClientesAgente> respuesta = new ArrayList<RsClientesAgente>();
		try {
			respuesta = agenteSp.llamarClientesAgenteSp(numDoc, tipoAgente,
					codAgente, snActivas, campoin1, campoin2, campoin3,
					campoin4, campoin5);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}