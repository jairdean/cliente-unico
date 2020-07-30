package com.equivida.sise.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsInformeSiniestros;
import com.equivida.sise.sp.InformeSiniestrosSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class InfoSiniestrosSpTest {

	static InformeSiniestrosSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (InformeSiniestrosSpRemoto) ContextUtil
				.lookup("java:InformeSiniestrosSp/remote");
	}

	@Test
	public void testLlamarSp() {

		Integer cod_suc = 1;
		Integer cod_ramo = 25;
		Integer nro_stro = 102667;
		String campoin1 = "";
		String campoin2 = "";
		String campoin3 = "";
		String campoin4 = "";
		String campoin5 = "";

		try {
			List<RsInformeSiniestros> respuestaLista = remoto
					.llamarInformeSiniestrosSp(cod_suc, cod_ramo, nro_stro,
							campoin1, campoin2, campoin3, campoin4, campoin5);

			for (RsInformeSiniestros respuesta : respuestaLista) {

				System.out.println("resp: consulta poliza "
						+ respuesta.getEjercicio());
				System.out.println(respuesta.getEstado());
				System.out.println(respuesta.getAsegurado());
				System.out.println(respuesta.getFecha_stro());
				System.out.println(respuesta.getTipo_estimacion());
				System.out.println(respuesta.getValor_estimado());
				System.out.println(respuesta.getCod_suc());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
