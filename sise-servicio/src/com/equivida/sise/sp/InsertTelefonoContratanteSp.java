package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.sise.rs.InsertTelefonoContratante;

@Local
public interface InsertTelefonoContratanteSp {

	InsertTelefonoContratante llamarInsertTelefonoPersonaSp(BigDecimal id_persona_wkf, BigDecimal cod_tipo_telef,
			String txt_telefono) throws SQLException;
}