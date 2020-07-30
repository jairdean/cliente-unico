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

import com.equivida.crm.rs.ListaBeneficiariosRs;
import com.equivida.crm.sp.ListaBeneficiariosSp;
import com.equivida.crm.ws.ListaBeneficiariosWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaBeneficiariosWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaBeneficiariosWS")
@Remote(ListaBeneficiariosWS.class)
public class ListaBeneficiariosWsImpl implements ListaBeneficiariosWS {

	@EJB
	private ListaBeneficiariosSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "sp_lista_benef_pol")
	public List<ListaBeneficiariosRs> sp_lista_benef_pol(
			Integer codigoSucursal, Integer ramo, Long numeroPoliza)
			throws SQLException {
		List<ListaBeneficiariosRs> respuesta = new ArrayList<ListaBeneficiariosRs>();
		try {
			respuesta = agenteSp.listaBeneficiariosSp(codigoSucursal, ramo,
					numeroPoliza);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}
