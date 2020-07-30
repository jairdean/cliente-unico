package com.equivida.sise.ws;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsAseguradosPoliza;

@WebService
@SOAPBinding(style = Style.RPC)
public interface AseguradosPolizaWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsAseguradosPoliza> ws_sise_list_aseg_poliza(
			@WebParam(name = "cod_suc") BigDecimal cod_suc,
			@WebParam(name = "cod_ramo") BigDecimal cod_ramo,
			@WebParam(name = "nro_pol") BigDecimal nro_pol,
			@WebParam(name = "sn_solo_activos") Integer sn_solo_activos,
			@WebParam(name = "fec_desde") String fec_desde,
			@WebParam(name = "fec_hasta") String fec_hasta,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);
}