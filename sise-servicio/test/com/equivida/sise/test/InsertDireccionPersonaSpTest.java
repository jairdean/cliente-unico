package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.InsertDireccionPersona;
import com.equivida.sise.sp.InsertDireccionPersonaSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class InsertDireccionPersonaSpTest {

	private InsertDireccionPersonaSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (InsertDireccionPersonaSpRemoto) ContextUtil
				.lookup("java:InsertDireccionPersonaSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal id_persona = BigDecimal.valueOf(2755806);
		BigDecimal cod_tipo_dir = BigDecimal.valueOf(1);
		String txt_direccion = "azul";
		BigDecimal cod_municipio = BigDecimal.valueOf(1);
		BigDecimal cod_dpto = BigDecimal.valueOf(1);
		BigDecimal cod_pais = BigDecimal.valueOf(1);
		String txt_edificio = "edif";
		String txt_urbanizacion = "urb";
		String txt_sector = "sec";
		String campoin1 = "c1";
		String campoin2 = "c2";
		String campoin3 = "c3";
		String campoin4 = "c4";
		String campoin5 = "c5";

		InsertDireccionPersona respuesta;
		try {
			respuesta = remoto.llamarInsertDireccionPersonaSp(id_persona,
					cod_tipo_dir, txt_direccion, cod_municipio, cod_dpto,
					cod_pais, txt_edificio, txt_urbanizacion, txt_sector,
					campoin1, campoin2, campoin3, campoin4, campoin5);

			System.out.println("resp: "+respuesta.getIdDireccion());
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
