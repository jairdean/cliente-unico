package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsEstadoCuenta;
import com.equivida.sise.sp.EstadoCuentaSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class EstadoCuentaSpTest {

	private EstadoCuentaSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (EstadoCuentaSpRemoto) ContextUtil
				.lookup("java:EstadoCuentaSp/remote");
	}

	@Test
	public void testLlamarSp() {

		Integer cod_suc = 2;
		Integer cod_ramo = 59;
		BigDecimal nro_pol = new BigDecimal(1590000860);
		String fec_desde = "2012/05/01";
		String fec_hasta = "2012/10/01";
		String campoin1 = null;
		String campoin2 = null;
		String campoin3 = null;
		String campoin4 = null;
		String campoin5 = null;

		List<RsEstadoCuenta> respuesta;
		try {
			respuesta = remoto.llamarEstadoCuentaSp(cod_suc, cod_ramo, nro_pol,
					fec_desde, fec_hasta, campoin1, campoin2, campoin3,
					campoin4, campoin5);

		System.out.println(respuesta);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
