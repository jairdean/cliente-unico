/**
 * 
 */
package com.equivida.sise.ws.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.equivida.sise.rs.RsCumulosDePago;
import com.equivida.sise.sp.CumulosDePagoSp;
import com.equivida.sise.ws.CumulosDePagoWs;

/**
 * @author daniel
 * 
 */
@Stateless(name = "CumulosDePagoWs")
@WebService(endpointInterface = "com.equivida.sise.ws.CumulosDePagoWs")
@Remote(CumulosDePagoWs.class)
public class CumulosDePagoWsImpl implements CumulosDePagoWs {

	@EJB
	private CumulosDePagoSp cumulosDePagoSp;

	@Override
	public List<RsCumulosDePago> consultar(
			@WebParam(name = "cedula") String numeroDocumento) {

		List<RsCumulosDePago> respuesta = null;
		try {
			respuesta = cumulosDePagoSp.consultar(numeroDocumento);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}