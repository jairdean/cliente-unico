package com.equivida.sise.ws;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsEstadoPrestamo;

@WebService
@SOAPBinding(style = Style.RPC)
public interface EstadoPrestamoBWs {

	// @WebResult(name = "RespuestaInsertMPersona")
	ArrayList<RsEstadoPrestamo> ws_sise_estado_prestamo_B(
			@WebParam(name = "cod_suc") Integer cod_suc,
			@WebParam(name = "cod_ramo") Integer cod_ramo,
			@WebParam(name = "nro_pol") BigDecimal nro_pol,
			@WebParam(name = "sn_activos") Integer sn_activos,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);

}