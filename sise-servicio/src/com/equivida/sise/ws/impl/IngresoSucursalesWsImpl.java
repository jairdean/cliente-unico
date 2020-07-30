package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsIngresoSucursales;
import com.equivida.sise.sp.IngresoSucursalesSp;
import com.equivida.sise.ws.IngresoSucursalesWs;

@Stateless(name = "IngresoSucursalesWs")
@WebService(endpointInterface = "com.equivida.sise.ws.IngresoSucursalesWs")
@Remote(IngresoSucursalesWs.class)
public class IngresoSucursalesWsImpl implements IngresoSucursalesWs {

	@EJB
	private IngresoSucursalesSp ingresoSucursalesSp;

	@Override
	@WebMethod
	public @WebResult(name = "IngresoSucursales")
	RsIngresoSucursales ws_sise_ingreso_sucursales(BigDecimal codPres,
			String sucursal, String direccion, String telefono,
			BigDecimal ubicacion, BigDecimal municipio,
			BigDecimal departamento, BigDecimal modo, BigDecimal codSucursal,
			BigDecimal tipoIdPres, String nroIdPress) {

		RsIngresoSucursales respuesta = new RsIngresoSucursales();
		try {
			respuesta = ingresoSucursalesSp.llamarIngresSucuralesSp(codPres,
					sucursal, direccion, telefono, ubicacion, municipio,
					departamento, modo, codSucursal, tipoIdPres, nroIdPress);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}