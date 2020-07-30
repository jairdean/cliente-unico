package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsRegistroMedicos;

@WebService
@SOAPBinding(style = Style.RPC)
public interface RegistroMedicoWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	RsRegistroMedicos llamarRegistroMedicoSp(
			@WebParam(name = "i_imp_tipoid_prestador") BigDecimal i_imp_tipoid_prestador,
			@WebParam(name = "i_txt_nro_iden_prestador") String i_txt_nro_iden_prestador,
			@WebParam(name = "i_imp_tipo_iden_medico") BigDecimal i_imp_tipo_iden_medico,
			@WebParam(name = "i_txt_nro_iden_medico") String i_txt_nro_iden_medico,
			@WebParam(name = "i_txt_licencia") String i_txt_licencia,
			@WebParam(name = "i_txt_apellido1_medico") String i_txt_apellido1_medico,
			@WebParam(name = "i_txt_apellido2_medico") String i_txt_apellido2_medico,
			@WebParam(name = "i_txt_nombres_medico") String i_txt_nombres_medico,
			@WebParam(name = "i_txt_mail_medico") String i_txt_mail_medico,
			@WebParam(name = "i_txt_telef_conv") String i_txt_telef_conv,
			@WebParam(name = "i_txt_telef_cel") String i_txt_telef_cel,
			@WebParam(name = "i_imp_operadora_cel") BigDecimal i_imp_operadora_cel,
			@WebParam(name = "i_cod_suc") BigDecimal i_cod_suc);

}