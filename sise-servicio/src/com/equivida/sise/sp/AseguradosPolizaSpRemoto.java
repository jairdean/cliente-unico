package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsAseguradosPoliza;

@Remote
public interface AseguradosPolizaSpRemoto {

	List<RsAseguradosPoliza> llamarAseguradosPolizaSp(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, Integer sn_solo_activos,
			Date fec_desde, Date fec_hasta, String campoin1, String campoin2,
			String campoin3, String campoin4, String campoin5)
			throws SQLException;
}