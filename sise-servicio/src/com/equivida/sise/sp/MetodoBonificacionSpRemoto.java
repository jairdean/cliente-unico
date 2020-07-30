package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsMetodoBonificacion;

@Remote
public interface MetodoBonificacionSpRemoto {

	RsMetodoBonificacion llamarMetodoBonificacionSp(BigDecimal i_modo,
			BigDecimal i_id_session, BigDecimal i_cod_procedimiento,
			BigDecimal i_cod_tipo_doc, String i_nro_doc, BigDecimal i_nro_op,
			BigDecimal i_nro_pieza_dental) throws SQLException;
}