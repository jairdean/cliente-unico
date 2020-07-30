package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsRegistroPresupuestoHeader;

@Remote
public interface RegistroPresupuestoHeaderSpRemoto {

	RsRegistroPresupuestoHeader llamarRegistroPresupuestoHeaderSp(
			BigDecimal i_imp_id_sesion, BigDecimal i_imp_nro_pol,
			BigDecimal i_nro_aseg, BigDecimal i_nro_pariente,
			BigDecimal i_imp_tipo_identifaseg, String i_txt_cedula,
			BigDecimal i_imp_cod_plan, BigDecimal i_imp_cod_red,
			BigDecimal i_imp_tipo_identifpres, String i_txt_identifpres,
			BigDecimal i_imp_cod_suc, BigDecimal i_imp_tipo_identifmdiag,
			String i_txt_identifmdiag, String i_tipo_reg) throws SQLException;
}