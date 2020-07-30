package com.equivida.sise.ws;

import java.util.ArrayList;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsCuotasPrestamo;

@WebService
@SOAPBinding(style = Style.RPC)
public interface CuotasPrestamoWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	ArrayList<RsCuotasPrestamo> ws_sise_cuotas_prestamo(
			@WebParam(name = "nro_perstamo") Integer nro_perstamo,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);

}