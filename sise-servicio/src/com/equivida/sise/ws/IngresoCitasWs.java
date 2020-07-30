package com.equivida.sise.ws;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsIngresoCitas;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IngresoCitasWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsIngresoCitas> llamarIngresoCitasSp(
			@WebParam(name = "i_imp_modo") BigDecimal i_imp_modo,
			@WebParam(name = "i_imp_tipo_doc") BigDecimal i_imp_tipo_doc,
			@WebParam(name = "i_txt_nro_ced") String i_txt_nro_ced,
			@WebParam(name = "i_imp_cod_aseg") BigDecimal i_imp_cod_aseg,
			@WebParam(name = "i_txt_apellido1") String i_txt_apellido1,
			@WebParam(name = "i_txt_apellido2") String i_txt_apellido2);

}