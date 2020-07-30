package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsRegistroClinicas;

@WebService
@SOAPBinding(style = Style.RPC)
public interface RegistroClinicasWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	RsRegistroClinicas llamarRegistroClinicasSp(
			@WebParam(name = "i_cod_tipo_doc") BigDecimal i_cod_tipo_doc,
			@WebParam(name = "i_nro_doc") String i_nro_doc,
			@WebParam(name = "i_txt_apellido1") String i_txt_apellido1,
			@WebParam(name = "i_cod_especialidad") BigDecimal i_cod_especialidad,
			@WebParam(name = "i_txt_direccion_principal") String i_txt_direccion_principal,
			@WebParam(name = "i_canton") BigDecimal i_canton,
			@WebParam(name = "i_provincia") BigDecimal i_provincia,
			@WebParam(name = "i_ciudad") BigDecimal i_ciudad,
			@WebParam(name = "i_sector") BigDecimal i_sector,
			@WebParam(name = "i_txt_direccion_mail") String i_txt_direccion_mail,
			@WebParam(name = "i_txt_telefono") String i_txt_telefono,
			@WebParam(name = "i_tele_cel") String i_tele_cel,
			@WebParam(name = "i_cod_operadora") BigDecimal i_cod_operadora,
			@WebParam(name = "i_contacto") String i_contacto);

}