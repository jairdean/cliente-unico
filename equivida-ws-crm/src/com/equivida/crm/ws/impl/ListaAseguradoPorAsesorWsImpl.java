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

import com.equivida.crm.rs.ListaAseguradoPorAsesorRs;
import com.equivida.crm.sp.ListaAseguradoPorAsesorSp;
import com.equivida.crm.ws.ListaAseguradoPorAsesorWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaAseguradoPorAsesorWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaAseguradoPorAsesorWS")
@Remote(ListaAseguradoPorAsesorWS.class)
public class ListaAseguradoPorAsesorWsImpl implements ListaAseguradoPorAsesorWS {

	@EJB
	private ListaAseguradoPorAsesorSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaCondicionPagoWsImpl")
	public List<ListaAseguradoPorAsesorRs> sp_lista_aseg_x_asesor(
			Integer asesoresActivos) throws SQLException {
		List<ListaAseguradoPorAsesorRs> respuesta = new ArrayList<ListaAseguradoPorAsesorRs>();
		try {
			respuesta = agenteSp.listaAseguradoPorAsesorSp(asesoresActivos);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
