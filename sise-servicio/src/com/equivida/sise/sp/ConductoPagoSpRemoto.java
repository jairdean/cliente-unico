package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsConductoPago;

@Remote
public interface ConductoPagoSpRemoto {

	List<RsConductoPago> llamarConductoPagoSp(BigDecimal id_persona)
			throws SQLException;
}