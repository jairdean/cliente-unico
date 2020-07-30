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

import com.equivida.crm.rs.ListaCondicionPagoDirectoRs;
import com.equivida.crm.sp.ListaCondicionPagoDirectoSp;
import com.equivida.crm.ws.ListaCondicionPagoDirectoWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCondicionPagoDirectoWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaCondicionPagoDirectoWS")
@Remote(ListaCondicionPagoDirectoWS.class)
public class ListaCondicionPagoDirectoWsImpl implements
		ListaCondicionPagoDirectoWS {

	@EJB
	private ListaCondicionPagoDirectoSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaCondicionPagoDirectoWsImpl")
	public List<ListaCondicionPagoDirectoRs> sp_lista_cond_pago_directo()
			throws SQLException {
		List<ListaCondicionPagoDirectoRs> respuesta = new ArrayList<ListaCondicionPagoDirectoRs>();
		try {
			respuesta = agenteSp.listaCondicionPagoDirectoSp();

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
