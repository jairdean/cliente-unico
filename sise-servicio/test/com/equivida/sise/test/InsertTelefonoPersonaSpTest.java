package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.InsertTelefonoPersona;
import com.equivida.sise.sp.InsertTelefonoPersonaSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class InsertTelefonoPersonaSpTest {

	private InsertTelefonoPersonaSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (InsertTelefonoPersonaSpRemoto) ContextUtil
				.lookup("java:InsertTelefonoPersonaSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal id_persona = BigDecimal.valueOf(123);
		BigDecimal cod_tipo_telef = BigDecimal.valueOf(1);
		String txt_telefono = "25888";
		String campoin1 = "c1";
		String campoin2 = "c2";
		String campoin3 = "c3";
		String campoin4 = "c4";
		String campoin5 = "c5";

		InsertTelefonoPersona respuesta;
		try {
			respuesta = remoto.llamarInsertTelefonoPersonaSp(id_persona,
					cod_tipo_telef, txt_telefono, campoin1, campoin2, campoin3,
					campoin4, campoin5);

			System.out.println("resp: " + respuesta.getIdTelefono());
			System.out.println(respuesta.getNumFilas());
			System.out.println(respuesta.getCodError());
			System.out.println(respuesta.getMsgError());
			System.out.println(respuesta.getCampoOut1());
			System.out.println(respuesta.getCampoOut2());
			System.out.println(respuesta.getCampoOut3());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
