package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsDisponibleRetiro;
import com.equivida.sise.sp.DisponibleRetiroSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class DisponibleRetiroSpTest {

	static DisponibleRetiroSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (DisponibleRetiroSpRemoto) ContextUtil
				.lookup("java:DisponibleRetiroSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal cod_suc = new BigDecimal(1);
		BigDecimal cod_ramo = new BigDecimal(59);
		BigDecimal nro_pol = new BigDecimal(1590000869);
		Timestamp fec_hasta = new Timestamp(new Date().getTime());
		Integer sn_muestra = -1;
		String campoin1 = "1";
		String campoin2 = "1";
		String campoin3 = "1";
		String campoin4 = "1";
		String campoin5 = "1";

		List<RsDisponibleRetiro> respuesta;

		try {
			;
			respuesta = remoto.llamarDisponibleRetiroSp(cod_suc, cod_ramo,
					nro_pol, fec_hasta, sn_muestra, campoin1, campoin2,
					campoin3, campoin4, campoin5);

			for (RsDisponibleRetiro rsDisponiblePrestamo : respuesta) {
				System.out.println("resp: consulta poliza "
						+ rsDisponiblePrestamo.getSaldoCuenta());
				System.out.println(rsDisponiblePrestamo.getCostoRescate());
				System.out.println(rsDisponiblePrestamo.getSaldoPrestamo());
				System.out.println(rsDisponiblePrestamo.getValorRescate());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
