package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsDisponiblePrestamo;

@Remote
public interface DisponiblePrestamoSpRemoto {

	List<RsDisponiblePrestamo> llamarDisponiblePrestamoSp(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, Date fec_hasta,
			Integer sn_muestra, String campoin1, String campoin2,
			String campoin3, String campoin4, String campoin5)
			throws SQLException;
}
