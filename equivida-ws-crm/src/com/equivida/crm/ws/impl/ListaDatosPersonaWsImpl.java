/**
 * 
 */
package com.equivida.crm.ws.impl;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.crm.rs.ListaDatosPersonaRs;
import com.equivida.crm.sp.ListaDatosPersonaSp;
import com.equivida.crm.ws.ListaDatosPersonaWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaDatosPersonaWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaDatosPersonaWS")
@Remote(ListaDatosPersonaWS.class)
public class ListaDatosPersonaWsImpl implements ListaDatosPersonaWS {

	@EJB
	private ListaDatosPersonaSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "sp_lista_datos_persona")
	public ListaDatosPersonaRs sp_lista_datos_persona(Integer id_persona,
			String numeroDocumento) throws SQLException {
		ListaDatosPersonaRs respuesta = null;
		try {
			respuesta = agenteSp.listaDatosPersonaSp(id_persona,
					numeroDocumento);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}
