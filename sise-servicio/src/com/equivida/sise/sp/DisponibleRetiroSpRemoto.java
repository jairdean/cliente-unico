package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsDisponibleRetiro;

@Remote
public interface DisponibleRetiroSpRemoto {

	List<RsDisponibleRetiro> llamarDisponibleRetiroSp(BigDecimal cod_sucursal,
			BigDecimal cod_ramo, BigDecimal nro_poliza, Date fec_hasta,
			Integer sn_muestra, String campoin1, String campoin2,
			String campoin3, String campoin4, String campoin5)
			throws SQLException;
}