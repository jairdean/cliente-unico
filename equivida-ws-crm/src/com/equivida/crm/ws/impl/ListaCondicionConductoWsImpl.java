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

import com.equivida.crm.rs.ListaCondicionConductoRs;
import com.equivida.crm.sp.ListaCondicionConductoSp;
import com.equivida.crm.ws.ListaCondicionConductoWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCondicionConductoWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaCondicionConductoWS")
@Remote(ListaCondicionConductoWS.class)
public class ListaCondicionConductoWsImpl implements ListaCondicionConductoWS {

	@EJB
	private ListaCondicionConductoSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaCondicionConductoImpl")
	public List<ListaCondicionConductoRs> sp_lista_cond_conducto()
			throws SQLException {
		List<ListaCondicionConductoRs> respuesta = new ArrayList<ListaCondicionConductoRs>();
		try {
			respuesta = agenteSp.listaCondicionConductoSp();

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
