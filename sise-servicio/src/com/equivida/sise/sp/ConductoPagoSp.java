package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.rs.RsConductoPago;

@Local
public interface ConductoPagoSp {

	List<RsConductoPago> llamarConductoPagoSp(BigDecimal id_persona)
			throws SQLException;
}