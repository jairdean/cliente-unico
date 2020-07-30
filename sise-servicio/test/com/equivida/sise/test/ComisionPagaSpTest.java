package com.equivida.sise.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsComisionPagada;
import com.equivida.sise.sp.ComisionPagadaSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class ComisionPagaSpTest {

	private ComisionPagadaSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (ComisionPagadaSpRemoto) ContextUtil
				.lookup("java:ComisionPagadaSp/remote");
	}

	@Test
	public void testLlamarSp() {

		Integer cod_tipo_agente = 1;
		Integer cod_agente = 548;
		String fec_desde = "2000/10/10";
		String fec_hasta = "2010/10/12";
		String campoin1 = "4";
		String campoin2 = "4";
		String campoin3 = "4";
		String campoin4 = "4";
		String campoin5 = "5";

		List<RsComisionPagada> respuesta;
		try {
			respuesta = remoto.llamarComsionPagadaSp(cod_tipo_agente,
					cod_agente, fec_desde, fec_hasta, campoin1, campoin2,
					campoin3, campoin4, campoin5);

			for (RsComisionPagada rsCuotasPrestamo : respuesta) {

				System.out.println("resp: -----------------"
						+ rsCuotasPrestamo.getComprobante());
				System.out.println(rsCuotasPrestamo.getCuentaCorrienta());
				System.out.println(rsCuotasPrestamo.getNroComprobante());
				System.out.println(rsCuotasPrestamo.getValor());
				System.out.println(rsCuotasPrestamo.getNombreBanco());
				System.out.println(rsCuotasPrestamo.getCodigosucursal());
				System.out.println(rsCuotasPrestamo.getFecha());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
