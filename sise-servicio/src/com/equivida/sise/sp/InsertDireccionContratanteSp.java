package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.sise.rs.InsertDireccionContratante;

/**
 * 
 * @author Daniel Cardenas
 *
 */
@Local
public interface InsertDireccionContratanteSp {

	InsertDireccionContratante llamarInsertDireccionContratanteSp(BigDecimal id_persona_wkf, BigDecimal cod_tipo_dir,
			String txt_direccion, BigDecimal cod_municipio, BigDecimal cod_dpto, BigDecimal cod_pais)
			throws SQLException;
}