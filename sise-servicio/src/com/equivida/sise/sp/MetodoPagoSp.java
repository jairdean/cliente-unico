package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.Local;

import com.equivida.sise.rs.RsMetodoPago;

@Local
public interface MetodoPagoSp {

	RsMetodoPago llamarMetodoPagoSp(BigDecimal i_modo, BigDecimal i_id_session,
			BigDecimal i_cod_procedimiento, BigDecimal i_nro_op,
			BigDecimal i_nro_factura, BigDecimal i_nro_pieza_dental)
			throws SQLException;
}