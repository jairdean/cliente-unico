package com.equivida.sise.ws;

import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsEstadoPrestamo;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EstadoPrestamoAWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	ArrayList<RsEstadoPrestamo> ws_sise_estado_prestamo_A(
			@WebParam(name = "cod_tipo_doc") Integer cod_tipo_doc,
			@WebParam(name = "nro_doc") String nro_doc,
			@WebParam(name = "sn_activos") Integer sn_activos,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);

}