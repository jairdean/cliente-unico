package com.equivida.sise.ws.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.obj.DetalleRegistoPresupuesto;
import com.equivida.sise.rs.RsRegistroPresupuesto;
import com.equivida.sise.sp.RegistroPresupuestoSp;
import com.equivida.sise.ws.RegistroPresupuestoWs;

@Stateless(name = "RegistroPresupuestoWs")
@WebService(endpointInterface = "com.equivida.sise.ws.RegistroPresupuestoWs")
@Remote(RegistroPresupuestoWs.class)
public class RegistroPresupuestoWsImpl implements RegistroPresupuestoWs {

	@EJB
	private RegistroPresupuestoSp registroPresupuestoSp;

	@Override
	@WebMethod
	public @WebResult(name = "RegistroPresupuesto")
	List<RsRegistroPresupuesto> llamarRegistroPresupuestoSp(
			List<DetalleRegistoPresupuesto> detalle) {
		List<RsRegistroPresupuesto> respuesta = new ArrayList<RsRegistroPresupuesto>();
		try {
			respuesta = registroPresupuestoSp
					.llamarRegistroPresupuestoSp(detalle);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}

}