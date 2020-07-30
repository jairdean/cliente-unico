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

import com.equivida.crm.rs.ListaCuponesPendientesRs;
import com.equivida.crm.sp.ListaCuponesPendientesSp;
import com.equivida.crm.ws.ListaCuponesPendientesWS;
import com.equivida.crm.ws.ListaPagosWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCuponesPendientesWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaCuponesPendientesWS")
@Remote(ListaPagosWS.class)
public class ListaCuponesPendientesWsImpl implements ListaCuponesPendientesWS {

	@EJB
	private ListaCuponesPendientesSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaCuponesPendientesWsImpl")
	public List<ListaCuponesPendientesRs> sp_lista_cupones_pend(
			Integer idPersona) throws SQLException {
		List<ListaCuponesPendientesRs> respuesta = new ArrayList<ListaCuponesPendientesRs>();
		try {
			respuesta = agenteSp.listaCuponesPendientesSp(idPersona,1,"1");

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
