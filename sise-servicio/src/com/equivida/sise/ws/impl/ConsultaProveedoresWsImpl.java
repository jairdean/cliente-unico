package com.equivida.sise.ws.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsConsultaProveedores;
import com.equivida.sise.sp.ConsultaProveedoresSp;
import com.equivida.sise.ws.ConsultaProveedoresWs;

@Stateless(name = "ConsultaProveedoresWs")
@WebService(endpointInterface = "com.equivida.sise.ws.ConsultaProveedoresWs")
@Remote(ConsultaProveedoresWs.class)
public class ConsultaProveedoresWsImpl implements ConsultaProveedoresWs {

	@EJB
	private ConsultaProveedoresSp consultaProveedoresSp;

	@Override
	@WebMethod
	public @WebResult(name = "ConsultaProveedores")
	List<RsConsultaProveedores> llamarConsultaProveedoresSp(String nroDoc,
			Integer snActivo) {
		List<RsConsultaProveedores> respuesta = null;

		try {
			respuesta = consultaProveedoresSp.llamarConsultaProveedoresSp(
					nroDoc, snActivo);
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}