package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsEstadoPrestamo;
import com.equivida.sise.sp.EstadoPrestamoBSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class EstadoPretamoSpTest {

	// private EstadoPrestamoSpRemoto remoto;

	private EstadoPrestamoBSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (EstadoPrestamoBSpRemoto) ContextUtil.lookup("java:EstadoPrestamoBSp/remote");
	}

	@Test
	public void testLlamarSp() {

		// Integer cod_tipo_doc = 1;
		// String nro_doc = "0601381593";
		// Integer id_persona = 2746812;
		Integer cod_suc = 1;
		Integer cod_ramo = 59;
		BigDecimal nro_pol = new BigDecimal(1590000869);
		Integer sn_activos = 0;
		String campoin1 = null;
		String campoin2 = null;
		String campoin3 = null;
		String campoin4 = null;
		String campoin5 = null;

		ArrayList<RsEstadoPrestamo> respuesta = new ArrayList<RsEstadoPrestamo>();

		/*
		 * try { respuesta = remoto.llamarEstadoPrestamoSp(cod_tipo_doc, nro_doc,
		 * sn_activos, campoin1, campoin2, campoin3, campoin4, campoin5);
		 * 
		 * for (RsEstadoPrestamo rsEstadoPrestamo : respuesta) {
		 * 
		 * System.out.println("resp: >>>>>>>>>>>>" +
		 * rsEstadoPrestamo.getCodigoSucursal());
		 * System.out.println(rsEstadoPrestamo.getCodigoRamo());
		 * System.out.println(rsEstadoPrestamo.getNumeroPoliza());
		 * System.out.println(rsEstadoPrestamo.getNumeroPrestamo());
		 * System.out.println(rsEstadoPrestamo.getFechaConcesion());
		 * System.out.println(rsEstadoPrestamo.getNumeroTasa());
		 * System.out.println(rsEstadoPrestamo.getImpCapital());
		 * System.out.println(rsEstadoPrestamo.getDevoluciones());
		 * System.out.println(rsEstadoPrestamo.getSnActivo());
		 * 
		 * }
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// --------------------tipo B

		try {
			respuesta = remoto.llamarEstadoPrestamoSp(cod_suc, cod_ramo, nro_pol, sn_activos, campoin1, campoin2,
					campoin3, campoin4, campoin5);

			for (RsEstadoPrestamo rsEstadoPrestamo : respuesta) {

				System.out.println("resp: >>>>>>>>>>>>" + rsEstadoPrestamo.getCodigoSucursal());
				System.out.println(rsEstadoPrestamo.getCodigoRamo());
				System.out.println(rsEstadoPrestamo.getNumeroPoliza());
				System.out.println(rsEstadoPrestamo.getNumeroPrestamo());
				System.out.println(rsEstadoPrestamo.getFechaConcesion());
				System.out.println(rsEstadoPrestamo.getNumeroTasa());
				System.out.println(rsEstadoPrestamo.getImpCapital());
				System.out.println(rsEstadoPrestamo.getDevoluciones());
				System.out.println(rsEstadoPrestamo.getSnActivo());

			}

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
