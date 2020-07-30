package com.equivida.sise.sp;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Remote;

import com.equivida.sise.rs.RsCoberturaPolizas;

@Remote
public interface CoberturaPolizaSpRemoto {

	List<RsCoberturaPolizas> llamarCoberturaPolizaSp(BigDecimal cod_suc,
			BigDecimal cod_ramo, BigDecimal nro_pol, String campoin1,
			String campoin2, String campoin3, String campoin4, String campoin5)
			throws SQLException;
}