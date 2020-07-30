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

import com.equivida.crm.rs.ListaCondicionTarjetaExpiradaRs;
import com.equivida.crm.sp.ListaCondicionTarjetaExpiradaSp;
import com.equivida.crm.ws.ListaCondicionTarjetaExpiradaWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCondicionTarjetaExpiradaWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaCondicionTarjetaExpiradaWS")
@Remote(ListaCondicionTarjetaExpiradaWS.class)
public class ListaCondicionTarjetaExpiradaWsImpl implements
		ListaCondicionTarjetaExpiradaWS {

	@EJB
	private ListaCondicionTarjetaExpiradaSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaCondicionTarjetaExpiradaImpl")
	public List<ListaCondicionTarjetaExpiradaRs> sp_lista_cond_tarj_exp()
			throws SQLException {
		List<ListaCondicionTarjetaExpiradaRs> respuesta = new ArrayList<ListaCondicionTarjetaExpiradaRs>();
		try {
			respuesta = agenteSp.listaCondicionTarjetaExpiradaSp();

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
