package com.equivida.sise.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsCuotasPrestamo;
import com.equivida.sise.sp.CuotasPrestamoSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class CuotasPretamoSpTest {

	private CuotasPrestamoSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (CuotasPrestamoSpRemoto) ContextUtil
				.lookup("java:CuotasPrestamoSp/remote");
	}

	@Test
	public void testLlamarSp() {

		Integer nro_perstamo = 640;
		String campoin1 = "1";
		String campoin2 = "2";
		String campoin3 = "3";
		String campoin4 = "4";
		String campoin5 = "5";

		ArrayList<RsCuotasPrestamo> respuesta;
		try {
			respuesta = remoto.llamarCuotasPrestamoSp(nro_perstamo, campoin1,
					campoin2, campoin3, campoin4, campoin5);

			for (RsCuotasPrestamo rsCuotasPrestamo : respuesta) {

				System.out.println("resp: -----------------"
						+ rsCuotasPrestamo.getNumeroCuota());
				System.out.println(rsCuotasPrestamo.getImpCapital());
				System.out.println(rsCuotasPrestamo.getImpInteres());
				System.out.println(rsCuotasPrestamo.getImpCuota());
				System.out.println(rsCuotasPrestamo.getEstado());
				System.out.println(rsCuotasPrestamo.getFechaVencimiento());
				System.out.println(rsCuotasPrestamo.getImpSaldo());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
