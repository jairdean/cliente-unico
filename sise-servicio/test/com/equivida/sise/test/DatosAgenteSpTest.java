package com.equivida.sise.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsDatosAgente;
import com.equivida.sise.sp.DatosAgenteSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class DatosAgenteSpTest {

	static DatosAgenteSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (DatosAgenteSpRemoto) ContextUtil
				.lookup("java:DatosAgenteSp/remote");
	}

	@Test
	public void testLlamarSp() {
		String nro_doc = "1790048772001";
		String campo1 = "";
		String campo2 = "";
		String campo3 = "";
		String campo4 = "";
		String campo5 = "";

		try {
			List<RsDatosAgente> respuestaLista = remoto.llamarDatosAgenteSp(
					nro_doc, campo1, campo2, campo3, campo4, campo5);

			for (RsDatosAgente respuesta : respuestaLista) {

				System.out.println("resp: consulta poliza "
						+ respuesta.getMunicipio());
				System.out.println(respuesta.getRazonSocial());
				System.out.println(respuesta.getDireccion());
				System.out.println(respuesta.getIdPersona());
				System.out.println(respuesta.getNumeroDocumento());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
