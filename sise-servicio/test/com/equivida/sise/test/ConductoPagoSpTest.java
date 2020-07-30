package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsConductoPago;
import com.equivida.sise.sp.ConductoPagoSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class ConductoPagoSpTest {

	static ConductoPagoSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (ConductoPagoSpRemoto) ContextUtil
				.lookup("java:ConductoPagoSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal id_persona = BigDecimal.valueOf(1258);

		try {
			List<RsConductoPago> respuestaLista = remoto
					.llamarConductoPagoSp(id_persona);

			for (RsConductoPago respuesta : respuestaLista) {

				System.out.println("resp: consulta poliza "
						+ respuesta.getConductoPago());
				System.out.println(respuesta.getNumerCuentaTarjeta());
				System.out.println(respuesta.getAnio());
				System.out.println(respuesta.getImplimiteTarjeta());
				System.out.println(respuesta.getTipoCuenta());
				System.out.println(respuesta.getEstado());
				System.out.println(respuesta.getNumeroSeguridad());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
