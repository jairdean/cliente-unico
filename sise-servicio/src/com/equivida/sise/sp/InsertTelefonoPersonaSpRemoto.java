package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.sise.rs.InsertTelefonoPersona;

@Remote
public interface InsertTelefonoPersonaSpRemoto {

	InsertTelefonoPersona llamarInsertTelefonoPersonaSp(BigDecimal id_persona,
			BigDecimal cod_tipo_telef, String txt_telefono, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException;

}