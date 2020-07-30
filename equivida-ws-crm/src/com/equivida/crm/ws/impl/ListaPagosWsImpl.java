/**
 * 
 */
package com.equivida.crm.ws.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.crm.rs.ListaPagosRs;
import com.equivida.crm.sp.ListaPagosSp;
import com.equivida.crm.ws.ListaPagosWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaPagosWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaPagosWS")
@Remote(ListaPagosWS.class)
public class ListaPagosWsImpl implements ListaPagosWS {

	@EJB
	private ListaPagosSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaPagosWsImpl")
	public List<ListaPagosRs> sp_lista_pagos(Integer idPersona)
			throws SQLException {
		List<ListaPagosRs> respuesta = new ArrayList<ListaPagosRs>();
		try {
			respuesta = agenteSp.listaPagosSp(idPersona, 1, "1");

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
