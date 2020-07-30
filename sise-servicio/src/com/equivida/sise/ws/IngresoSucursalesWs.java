package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsIngresoSucursales;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IngresoSucursalesWs {

	// @WebResult(name = "RespuestaInsertMPersona")
	RsIngresoSucursales ws_sise_ingreso_sucursales(
			@WebParam(name = "codPres") BigDecimal codPres,
			@WebParam(name = "sucursal") String sucursal,
			@WebParam(name = "direccion") String direccion,
			@WebParam(name = "telefono") String telefono,
			@WebParam(name = "ubicacion") BigDecimal ubicacion,
			@WebParam(name = "municipio") BigDecimal municipio,
			@WebParam(name = "departamento") BigDecimal departamento,
			@WebParam(name = "modo") BigDecimal modo,
			@WebParam(name = "codSucursal") BigDecimal codSucursal,
			@WebParam(name = "tipoIdPres") BigDecimal tipoIdPres,
			@WebParam(name = "nroIdPress") String nroIdPress);

}