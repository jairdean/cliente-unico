package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsDisponiblePrestamo;
import com.equivida.sise.sp.DisponiblePrestamoSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class DisponiblePrestamoSpTest {

	static DisponiblePrestamoSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (DisponiblePrestamoSpRemoto) ContextUtil
				.lookup("java:DisponiblePrestamoSp/remote");
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

		List<RsDisponiblePrestamo> respuesta;
		
		try {
			respuesta = remoto.llamarDisponiblePrestamoSp(cod_suc, cod_ramo,
					nro_pol, fec_hasta, sn_muestra, campoin1, campoin2,
					campoin3, campoin4, campoin5);

			for (RsDisponiblePrestamo rsDisponiblePrestamo : respuesta) {
				System.out.println("resp: consulta poliza "
						+ rsDisponiblePrestamo.getNroCedula());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
