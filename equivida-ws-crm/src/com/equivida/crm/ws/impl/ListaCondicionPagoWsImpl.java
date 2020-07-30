/**
 * 
 */
package com.equivida.crm.ws.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.crm.rs.ListaConductoPagoRs;
import com.equivida.crm.sp.ListaConductoPagoSp;
import com.equivida.crm.util.DateUtil;
import com.equivida.crm.ws.ListaCondicionPagoWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCondicionPagoWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaCondicionPagoWS")
@Remote(ListaCondicionPagoWS.class)
public class ListaCondicionPagoWsImpl implements ListaCondicionPagoWS {

	@EJB
	private ListaConductoPagoSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaCondicionPagoWsImpl")
	public List<ListaConductoPagoRs> sp_lista_cond_pago(String fechaEmision,
			Integer codigoConducto) throws SQLException {
		List<ListaConductoPagoRs> respuesta = new ArrayList<ListaConductoPagoRs>();
		try {
			System.out.println(fechaEmision);
			respuesta = agenteSp.listaConductoPagoSp(
					fechaEmision, codigoConducto);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		} 
		return respuesta;
	}
}
