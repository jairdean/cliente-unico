package com.equivida.sise.ws;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsCertificadoIndividual;

@WebService
@SOAPBinding(style = Style.RPC)
public interface CertificadoIndividualWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsCertificadoIndividual> ws_sise_certificado_individual(
			@WebParam(name = "cod_suc") BigDecimal cod_suc,
			@WebParam(name = "cod_ramo") BigDecimal cod_ramo,
			@WebParam(name = "nro_pol") BigDecimal nro_pol,
			@WebParam(name = "nro_aseg") BigDecimal nro_aseg,
			@WebParam(name = "nro_doc") String nro_doc,
			@WebParam(name = "campoin1") String campoin1,
			@WebParam(name = "campoin2") String campoin2,
			@WebParam(name = "campoin3") String campoin3,
			@WebParam(name = "campoin4") String campoin4,
			@WebParam(name = "campoin5") String campoin5);

}