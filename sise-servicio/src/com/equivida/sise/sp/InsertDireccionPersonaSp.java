package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.sise.rs.InsertDireccionPersona;

@Local
public interface InsertDireccionPersonaSp {

	InsertDireccionPersona llamarInsertDireccionPersonaSp(
			BigDecimal id_persona, BigDecimal cod_tipo_dir,
			String txt_direccion, BigDecimal cod_municipio,
			BigDecimal cod_dpto, BigDecimal cod_pais, String txt_edificio,
			String txt_urbanizacion, String txt_sector, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException;
}