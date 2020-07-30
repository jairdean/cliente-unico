package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsActualizacionDatos;

@Remote
public interface ActualizacionDatosSpRemoto {

	RsActualizacionDatos llamarActualizacionDatosSp(BigDecimal i_imp_poliza,
			BigDecimal i_imp_nro_asegurado, BigDecimal i_imp_nro_pariente,
			BigDecimal i_imp_tipo_identificacion, String i_txt_identificacion,
			String i_txt_primer_apellido, String i_txt_segundo_apellido,
			String i_txt_nombres, Date i_dat_fecha_nacimiento,
			String i_txt_direccion_principal, BigDecimal i_imp_provincia,
			BigDecimal i_imp_canton, String i_txt_direccion_domicilio,
			BigDecimal i_imp_provinciad, BigDecimal i_imp_cantond,
			String i_txt_telefono_principal, String i_txt_telefono_celular,
			BigDecimal i_imp_operadora, String i_txt_direccion_mail)
			throws SQLException;
}