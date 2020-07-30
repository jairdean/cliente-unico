package com.equivida.sise.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsComisionNoPagada;
import com.equivida.sise.sp.ComisionNoPagadaSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class ComisionNoPagaSpTest {

	private ComisionNoPagadaSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (ComisionNoPagadaSpRemoto) ContextUtil
				.lookup("java:ComisionNoPagadaSp/remote");
	}

	@Test
	public void testLlamarSp() {

		Integer cod_tipo_agente = 1;
		Integer cod_agente = 548;
		String fec_hasta = "2006/10/10";
		String campoin1 = "4";
		String campoin2 = "4";
		String campoin3 = "4";
		String campoin4 = "4";
		String campoin5 = "5";

		List<RsComisionNoPagada> respuesta;
		try {
			respuesta = remoto.llamarComisionNoPagadaSp(cod_tipo_agente,
					cod_agente, fec_hasta, campoin1, campoin2, campoin3,
					campoin4, campoin5,null);

			for (RsComisionNoPagada rsCuotasPrestamo : respuesta) {

				System.out.println("resp: -----------------"
						+ rsCuotasPrestamo.getPoliza());
				System.out.println(rsCuotasPrestamo.getFechaCobroPago());
				System.out.println(rsCuotasPrestamo.getNumFactura());
				System.out.println(rsCuotasPrestamo.getImpPrima());
				System.out.println(rsCuotasPrestamo.getDebCred());
				System.out.println(rsCuotasPrestamo.getImpComisNormal());
				System.out.println(rsCuotasPrestamo.getPjeComision());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
