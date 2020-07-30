package com.equivida.sise.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsClientesAgente;
import com.equivida.sise.sp.ClientesAgenteSpRemote;
import com.equivida.sise.test.util.ContextUtil;

public class ClientesAgenteSpTest {

	static ClientesAgenteSpRemote remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (ClientesAgenteSpRemote) ContextUtil
				.lookup("java:ClientesAgenteSp/remote");
	}

	@Test
	public void testLlamarSp() {
		String numDoc = "1790048772001";
		Integer tipoAgente = null;
		Integer codAgente = null;
		Integer snActivas = -1;
		String campoin1 = null;
		String campoin2 = null;
		String campoin3 = null;
		String campoin4 = null;
		String campoin5 = null;

		List<RsClientesAgente> respuesta;
		try {
			respuesta = remoto.llamarClientesAgenteSp(numDoc, tipoAgente,
					codAgente, snActivas, campoin1, campoin2, campoin3,
					campoin4, campoin5);

			for (RsClientesAgente rsDatosPersona : respuesta) {

				System.out.println("resp: -----------------"
						+ rsDatosPersona.getNumeroDocumento());
				System.out.println(rsDatosPersona.getCodAseg());
				System.out.println(rsDatosPersona.getIdPersona());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
