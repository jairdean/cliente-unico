package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.equivida.sise.rs.RsEstadoCuenta;

@Local
public interface EstadoCuentaSp {

	List<RsEstadoCuenta> llamarEstadoCuentaSp(Integer cod_suc, Integer cod_ramo,
			BigDecimal nro_pol, String fec_desde, String fec_hasta,
			String campoin1, String campoin2, String campoin3, String campoin4,
			String campoin5) throws SQLException;
}