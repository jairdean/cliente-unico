package com.equivida.sise.ws.impl;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.dto.ContratanteDto;
import com.equivida.sise.rs.RsContratante;
import com.equivida.sise.sp.ContratanteSp;
import com.equivida.sise.ws.ContratantesWs;

@Stateless(name = "ContratantesWs")
@WebService(endpointInterface = "com.equivida.sise.ws.ContratantesWs")
@Remote(ContratantesWs.class)
public class ContratanteWsImpl implements ContratantesWs {

	@EJB
	private ContratanteSp contratanteSp;

	@Override
	@WebMethod(action = "ws_sise_sp_contratante", operationName = "ws_sise_sp_contratante")
	public @WebResult(name = "RsContratante") RsContratante ws_sise_sp_contratante(
			@WebParam(name = "contratanteData", partName = "contratanteData") ContratanteDto contratanteDto) {

		try {
			RsContratante respuesta = contratanteSp.llamarContratanteSp(contratanteDto);

			System.out.println("*******respuesta ws_sise_sp_contratante: " + respuesta.getMessage_wkf());
			return respuesta;
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);

		}

		return null;
	}

}