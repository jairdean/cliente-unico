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

import com.equivida.crm.rs.ListaAseguradoCuponPendienteRs;
import com.equivida.crm.sp.ListaAseguradoCuponPendienteSp;
import com.equivida.crm.ws.ListaAseguradoCuponPendienteWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaAseguradoCuponPendienteWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaAseguradoCuponPendienteWS")
@Remote(ListaAseguradoCuponPendienteWS.class)
public class ListaAseguradoCuponPendienteWsImpl implements
		ListaAseguradoCuponPendienteWS {

	@EJB
	private ListaAseguradoCuponPendienteSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaAseguradoCuponPendienteWsImpl")
	public List<ListaAseguradoCuponPendienteRs> sp_lista_aseg_cupon_pend()
			throws SQLException {
		List<ListaAseguradoCuponPendienteRs> respuesta = new ArrayList<ListaAseguradoCuponPendienteRs>();
		try {
			respuesta = agenteSp.listaAseguradoCuponPendienteSp();

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
