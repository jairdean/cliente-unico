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

import com.equivida.crm.rs.ListaConductoAniversarioRs;
import com.equivida.crm.sp.ListaConductoAniversarioSp;
import com.equivida.crm.ws.ListaCondicionAniversarioWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCondicionAniversarioWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaCondicionAniversarioWS")
@Remote(ListaCondicionAniversarioWS.class)
public class ListaCondicionAniversarioWsImpl implements
		ListaCondicionAniversarioWS {

	@EJB
	private ListaConductoAniversarioSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaCondicionAniversarioWsImpl")
	public List<ListaConductoAniversarioRs> sp_lista_cond_aniv(Integer meses,
			Integer codigoCobertura) throws SQLException {
		List<ListaConductoAniversarioRs> respuesta = new ArrayList<ListaConductoAniversarioRs>();
		try {
			respuesta = agenteSp.listaConductoAniversarioSp(meses,
					codigoCobertura);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
