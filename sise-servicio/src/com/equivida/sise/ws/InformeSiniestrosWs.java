package com.equivida.sise.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsInformeSiniestros;

@WebService
@SOAPBinding(style = Style.RPC)
public interface InformeSiniestrosWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsInformeSiniestros> ws_sise_info_siniestros(
			@WebParam(name = "cod_suc") Integer cod_suc,
			@WebParam(name = "cod_ramo") Integer cod_ramo,
			@WebParam(name = "nro_stro") Integer nro_stro,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);
}
