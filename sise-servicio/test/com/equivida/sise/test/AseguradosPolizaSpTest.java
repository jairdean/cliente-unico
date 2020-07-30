package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsAseguradosPoliza;
import com.equivida.sise.sp.AseguradosPolizaSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class AseguradosPolizaSpTest {

	static AseguradosPolizaSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (AseguradosPolizaSpRemoto) ContextUtil
				.lookup("java:AseguradosPolizaSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal cod_suc = new BigDecimal(1);
		BigDecimal cod_ramo = new BigDecimal(59);
		BigDecimal nro_pol = new BigDecimal(1590000869);
		Timestamp fec_desde = new Timestamp(new Date().getTime());
		Timestamp fec_hasta = new Timestamp(new Date().getTime());
		Integer sn_solo_activos = -1;
		String campoin1 = "1";
		String campoin2 = "1";
		String campoin3 = "1";
		String campoin4 = "1";
		String campoin5 = "1";

		List<RsAseguradosPoliza> respuesta;

		try {
			;
			respuesta = remoto.llamarAseguradosPolizaSp(cod_suc, cod_ramo,
					nro_pol, sn_solo_activos, fec_desde, fec_hasta, campoin1,
					campoin2, campoin3, campoin4, campoin5);

			for (RsAseguradosPoliza rsDisponiblePrestamo : respuesta) {
				System.out.println("resp: consulta poliza "
						+ rsDisponiblePrestamo.getApellido1());
				System.out.println(rsDisponiblePrestamo.getNombre());
				System.out.println(rsDisponiblePrestamo.getNroAsegurdo());
				System.out.println(rsDisponiblePrestamo.getEstadoActual());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
