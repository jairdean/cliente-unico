package com.equivida.sise.ws;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsInfoPoliza;

@WebService
@SOAPBinding(style = Style.RPC)
public interface InfoPolizaWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsInfoPoliza> ws_sise_info_poliza(
			@WebParam(name = "cod_suc") BigDecimal cod_suc,
			@WebParam(name = "cod_ramo") BigDecimal cod_ramo,
			@WebParam(name = "nro_pol") BigDecimal nro_pol,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);

}