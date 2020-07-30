package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsRegistroClinicas;
import com.equivida.sise.sp.RegistroClinicasSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class RegistroClinicasSpTest {

	static RegistroClinicasSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (RegistroClinicasSpRemoto) ContextUtil
				.lookup("java:RegistroClinicasSp/remote");
	}

	@Test
	public void testLlamarSp() {

		BigDecimal i_cod_tipo_doc = new BigDecimal(1);
		String i_nro_doc = "22";
		String i_txt_apellido1 = "22";
		BigDecimal i_cod_especialidad = new BigDecimal(1);
		String i_txt_direccion_principal = "22";
		BigDecimal i_canton = new BigDecimal(1);
		BigDecimal i_provincia = new BigDecimal(1);
		BigDecimal i_ciudad = new BigDecimal(1);
		BigDecimal i_sector = new BigDecimal(1);
		String i_txt_direccion_mail = "22";
		String i_txt_telefono = "22";
		String i_tele_cel = "22";
		BigDecimal i_cod_operadora = new BigDecimal(1);
		String i_contacto = "22";

		RsRegistroClinicas respuesta;
		try {
			respuesta = remoto.llamarRegistroClinicasSp(i_cod_tipo_doc,
					i_nro_doc, i_txt_apellido1, i_cod_especialidad,
					i_txt_direccion_principal, i_canton, i_provincia, i_ciudad,
					i_sector, i_txt_direccion_mail, i_txt_telefono, i_tele_cel,
					i_cod_operadora, i_contacto);

			System.out.println("resp: " + respuesta.getGrabo());
			System.out.println(respuesta.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
