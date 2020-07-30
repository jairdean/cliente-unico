package com.equivida.sise.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.obj.DetalleRegistoPresupuesto;
import com.equivida.sise.rs.RsRegistroPresupuesto;

@WebService
@SOAPBinding(style = Style.RPC)
public interface RegistroPresupuestoWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsRegistroPresupuesto> llamarRegistroPresupuestoSp(
			@WebParam(name = "detalleRegistroPresupuesto") List<DetalleRegistoPresupuesto> detalle);

}