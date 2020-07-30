package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsCoberturaPolizas;
import com.equivida.sise.sp.CoberturaPolizaSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class CoberturaPolizaSpTest {

	static CoberturaPolizaSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (CoberturaPolizaSpRemoto) ContextUtil.lookup("java:CoberturaPolizasSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal cod_suc = new BigDecimal(1);
		BigDecimal cod_ramo = new BigDecimal(50);
		BigDecimal nro_pol = new BigDecimal(1500002499);
		String campoin1 = "1";
		String campoin2 = "1";
		String campoin3 = "1";
		String campoin4 = "1";
		String campoin5 = "1";

		List<RsCoberturaPolizas> respuesta;
		try {
			respuesta = remoto.llamarCoberturaPolizaSp(cod_suc, cod_ramo, nro_pol, campoin1, campoin2, campoin3,
					campoin4, campoin5);

			System.out.println(respuesta);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
