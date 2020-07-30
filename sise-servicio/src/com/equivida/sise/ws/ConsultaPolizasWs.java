package com.equivida.sise.ws;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsConsultaPolizas;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ConsultaPolizasWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsConsultaPolizas> llamarConsultaPolizasSp(
			@WebParam(name = "nro_doc") String nro_doc,
			@WebParam(name = "id_persona") BigDecimal id_persona,
			@WebParam(name = "sn_activas") Integer sn_activas,
			@WebParam(name = "tipo_busca") Integer tipo_busca,
			@WebParam(name = "campo1") String campo1,
			@WebParam(name = "campo2") String campo2,
			@WebParam(name = "campo3") String campo3,
			@WebParam(name = "campo4") String campo4,
			@WebParam(name = "campo5") String campo5);

}