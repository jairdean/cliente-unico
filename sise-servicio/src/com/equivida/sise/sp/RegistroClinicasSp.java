package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.sise.rs.RsRegistroClinicas;

@Local
public interface RegistroClinicasSp {

	RsRegistroClinicas llamarRegistroClinicasSp(BigDecimal i_cod_tipo_doc,
			String i_nro_doc, String i_txt_apellido1,
			BigDecimal i_cod_especialidad, String i_txt_direccion_principal,
			BigDecimal i_canton, BigDecimal i_provincia, BigDecimal i_ciudad,
			BigDecimal i_sector, String i_txt_direccion_mail,
			String i_txt_telefono, String i_tele_cel,
			BigDecimal i_cod_operadora, String i_contacto) throws SQLException;
}