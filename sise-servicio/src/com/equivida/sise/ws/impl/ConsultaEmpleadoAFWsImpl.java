package com.equivida.sise.ws.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsConsultaEmpleadoAF;
import com.equivida.sise.sp.ConsultaEmpleadoAFSp;
import com.equivida.sise.ws.ConsultaEmpleadoAFWs;

@Stateless(name = "ConsultaEmpleadoAFWs")
@WebService(endpointInterface = "com.equivida.sise.ws.ConsultaEmpleadoAFWs")
@Remote(ConsultaEmpleadoAFWs.class)
public class ConsultaEmpleadoAFWsImpl implements ConsultaEmpleadoAFWs {

	@EJB
	private ConsultaEmpleadoAFSp consultaEmpleadoAFSp;

	@Override
	@WebMethod
	public @WebResult(name = "ConsultaEmpleadoAF")
	List<RsConsultaEmpleadoAF> llamarConsultaEmpleadoAFSp(String cedula) {
		List<RsConsultaEmpleadoAF> respuesta = null;

		try {
			respuesta = consultaEmpleadoAFSp.consultaEmpleadoAFSp(cedula);
		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}