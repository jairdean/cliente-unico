package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsActualizacionDatos;
import com.equivida.sise.sp.ActualizacionDatosSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class ActualizacionDatosSpTest {

	private ActualizacionDatosSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (ActualizacionDatosSpRemoto) ContextUtil
				.lookup("java:ActualizacionDatosSp/remote");
	}

	@Test
	public void testLlamarSp() {

		BigDecimal i_imp_poliza = new BigDecimal("3500000002");
		BigDecimal i_imp_nro_asegurado = BigDecimal.valueOf(1);
		BigDecimal i_imp_nro_pariente = BigDecimal.valueOf(0);
		BigDecimal i_imp_tipo_identificacion = BigDecimal.valueOf(1);
		String i_txt_identificacion = "1704738127";
		String i_txt_primer_apellido = "ar";
		String i_txt_segundo_apellido = "pe";
		String i_txt_nombres = "urb";
		Timestamp i_dat_fecha_nacimiento = null;
		String i_txt_direccion_principal = "c1";
		BigDecimal i_imp_provincia = BigDecimal.valueOf(1);
		BigDecimal i_imp_canton = null;
		String i_txt_direccion_domicilio = null;
		BigDecimal i_imp_provinciad = BigDecimal.valueOf(1);
		BigDecimal i_imp_cantond = BigDecimal.valueOf(1);
		String i_txt_telefono_principal = "c5";
		String i_txt_telefono_celular = null;
		BigDecimal i_imp_operadora = BigDecimal.valueOf(1);
		String i_txt_direccion_mail = "c5";

		RsActualizacionDatos respuesta;
		try {
			respuesta = remoto.llamarActualizacionDatosSp(i_imp_poliza,
					i_imp_nro_asegurado, i_imp_nro_pariente,
					i_imp_tipo_identificacion, i_txt_identificacion,
					i_txt_primer_apellido, i_txt_segundo_apellido,
					i_txt_nombres, i_dat_fecha_nacimiento,
					i_txt_direccion_principal, i_imp_provincia, i_imp_canton,
					i_txt_direccion_domicilio, i_imp_provinciad, i_imp_cantond,
					i_txt_telefono_principal, i_txt_telefono_celular,
					i_imp_operadora, i_txt_direccion_mail);

			System.out.println("resp: " + respuesta.getMessage());
			System.out.println(respuesta.getGrabo());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
