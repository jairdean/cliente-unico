/**
 * 
 */
package com.equivida.crm.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.crm.rs.ListaCoberturasRs;
import com.equivida.crm.sp.ListaCoberturasSp;
import com.equivida.crm.ws.ListaBeneficiariosWS;
import com.equivida.crm.ws.ListaCoberturasWS;

/**
 * @author saviasoft5
 * 
 */
@Stateless(name = "ListaCoberturasWS")
@WebService(endpointInterface = "com.equivida.crm.ws.ListaCoberturasWS")
@Remote(ListaBeneficiariosWS.class)
public class ListaCoberturasWsImpl implements ListaCoberturasWS {

	@EJB
	private ListaCoberturasSp agenteSp;

	@Override
	@WebMethod
	@WebResult(name = "ListaBeneficiariosWsImpl")
	public List<ListaCoberturasRs> sp_lista_coberturas(Integer codigoSucursal,
			Integer ramo, BigDecimal numeroPoliza) throws SQLException {
		List<ListaCoberturasRs> respuesta = new ArrayList<ListaCoberturasRs>();
		try {
			respuesta = agenteSp.listaCoberturasSp(codigoSucursal, ramo,
					numeroPoliza);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}
