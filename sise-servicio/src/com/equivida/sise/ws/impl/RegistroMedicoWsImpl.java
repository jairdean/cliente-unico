package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsRegistroMedicos;
import com.equivida.sise.sp.RegistroMedicosSp;
import com.equivida.sise.ws.RegistroMedicoWs;

@Stateless(name = "RegistroMedicoWs")
@WebService(endpointInterface = "com.equivida.sise.ws.RegistroMedicoWs")
@Remote(RegistroMedicoWs.class)
public class RegistroMedicoWsImpl implements RegistroMedicoWs {

	@EJB
	private RegistroMedicosSp registroMedicosSp;

	@Override
	@WebMethod
	public @WebResult(name = "RegistroMedico")
	RsRegistroMedicos llamarRegistroMedicoSp(BigDecimal i_imp_tipoid_prestador,
			String i_txt_nro_iden_prestador, BigDecimal i_imp_tipo_iden_medico,
			String i_txt_nro_iden_medico, String i_txt_licencia,
			String i_txt_apellido1_medico, String i_txt_apellido2_medico,
			String i_txt_nombres_medico, String i_txt_mail_medico,
			String i_txt_telef_conv, String i_txt_telef_cel,
			BigDecimal i_imp_operadora_cel, BigDecimal i_cod_suc) {

		RsRegistroMedicos respuesta = null;
		try {
			respuesta = registroMedicosSp.llamarRegistroMedicosSp(
					i_imp_tipoid_prestador, i_txt_nro_iden_prestador,
					i_imp_tipo_iden_medico, i_txt_nro_iden_medico,
					i_txt_licencia, i_txt_apellido1_medico,
					i_txt_apellido2_medico, i_txt_nombres_medico,
					i_txt_mail_medico, i_txt_telef_conv, i_txt_telef_cel,
					i_imp_operadora_cel, i_cod_suc);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}
		return respuesta;
	}
}