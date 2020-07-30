package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import javax.ejb.Remote;

import com.equivida.sise.rs.RespuestaInsertMPersona;

@Remote
public interface InsertMPersonaSpRemoto {

	RespuestaInsertMPersona llamarSp(String txt_apellido1,
			String txt_apellido2, String txt_nombre, BigDecimal cod_tipo_doc,
			String nro_doc, BigDecimal cod_tipo_iva, String nro_nit,
			Date fec_nac, String txt_lugar_nac, String txt_sexo,
			BigDecimal cod_est_civil, String cod_tipo_persona,
			String txt_origen, String txt_nombres_conyuge,
			String txt_apellidos_conyuge, BigDecimal cod_tipo_doc_conyuge,
			String nro_doc_conyuge, String campo_in_1, String campo_in_2,
			String campo_in_3, String campo_in_4, String campo_in_5)
			throws SQLException;

}
