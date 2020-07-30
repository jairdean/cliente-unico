package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.equivida.sise.rs.RespuestaInsertMPersona;
import com.equivida.sise.sp.InsertMPersonaSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class InsertMPersonaSpTest {

	static InsertMPersonaSpRemoto remoto;

	@BeforeClass
	public static void setUp() throws Exception {
		remoto = (InsertMPersonaSpRemoto) ContextUtil
				.lookup("java:InsertMPersonaSp/remote");
	}

	@Test
	public void testLlamarSp() {

		String txt_apellido1 = "NUÑEZ";
		String txt_apellido2 = "NARIÑO";
		String txt_nombre = "IVÁN";
		BigDecimal cod_tipo_doc = BigDecimal.valueOf(1);
		String nro_doc = "1212121212";
		BigDecimal cod_tipo_iva = BigDecimal.valueOf(1);
		String nro_nit = "";
		Timestamp fec_nac = new Timestamp(new Date().getTime());
		String txt_lugar_nac = "ECU";
		String txt_sexo = "M";
		BigDecimal cod_est_civil = BigDecimal.valueOf(1l);
		String cod_tipo_persona = "F";
		String txt_origen = "A";
		String txt_nombres_conyuge = "CC";
		String txt_apellidos_conyuge = "DD";
		BigDecimal cod_tipo_doc_conyuge = BigDecimal.valueOf(1l);
		String nro_doc_conyuge = "001";
		String campo_in_1 = "1";
		String campo_in_2 = "2";
		String campo_in_3 = "3";
		String campo_in_4 = "4";
		String campo_in_5 = "5";

		RespuestaInsertMPersona respuesta;
		try {
			respuesta = remoto.llamarSp(txt_apellido1, txt_apellido2,
					txt_nombre, cod_tipo_doc, nro_doc, cod_tipo_iva, nro_nit,
					fec_nac, txt_lugar_nac, txt_sexo, cod_est_civil,
					cod_tipo_persona, txt_origen, txt_nombres_conyuge,
					txt_apellidos_conyuge, cod_tipo_doc_conyuge,
					nro_doc_conyuge, campo_in_1, campo_in_2, campo_in_3,
					campo_in_4, campo_in_5);

			System.out.println(respuesta.getIdPersona());
			System.out.println(respuesta.getNumFilas());
			System.out.println(respuesta.getIdProceso());
			System.out.println(respuesta.getTxtError());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
