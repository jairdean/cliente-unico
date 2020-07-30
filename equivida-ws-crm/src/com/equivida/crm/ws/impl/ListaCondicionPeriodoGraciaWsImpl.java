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

import com.equivida.crm.rs.ListaCondicionPeriodoGraciaRs;
import com.equivida.crm.sp.ListaCondicionPeriodoGraciaSp;
import com.equivida.crm.ws.ListaCondicionConductoWS;
import com.equivida.crm.ws.ListaCondicionPeriodoGraciaWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCondicionPeriodoGraciaWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaCondicionPeriodoGraciaWS")
@Remote(ListaCondicionConductoWS.class)
public class ListaCondicionPeriodoGraciaWsImpl implements
		ListaCondicionPeriodoGraciaWS {

	@EJB
	private ListaCondicionPeriodoGraciaSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaCondicionPeriodoGraciaImpl")
	public List<ListaCondicionPeriodoGraciaRs> sp_lista_cond_pgracia()
			throws SQLException {
		List<ListaCondicionPeriodoGraciaRs> respuesta = new ArrayList<ListaCondicionPeriodoGraciaRs>();
		try {
			respuesta = agenteSp.listaCondicionPeriodoGraciaSp();

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
