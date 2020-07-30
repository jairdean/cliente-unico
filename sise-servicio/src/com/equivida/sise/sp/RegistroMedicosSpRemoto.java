package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsRegistroMedicos;

@Remote
public interface RegistroMedicosSpRemoto {

	RsRegistroMedicos llamarRegistroMedicosSp(
			BigDecimal i_imp_tipoid_prestador, String i_txt_nro_iden_prestador,
			BigDecimal i_imp_tipo_iden_medico, String i_txt_nro_iden_medico,
			String i_txt_licencia, String i_txt_apellido1_medico,
			String i_txt_apellido2_medico, String i_txt_nombres_medico,
			String i_txt_mail_medico, String i_txt_telef_conv,
			String i_txt_telef_cel, BigDecimal i_imp_operadora_cel,
			BigDecimal i_cod_suc) throws SQLException;
}