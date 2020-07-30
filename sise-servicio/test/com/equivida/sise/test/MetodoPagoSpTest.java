package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsMetodoPago;
import com.equivida.sise.sp.MetodoPagoSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class MetodoPagoSpTest {

	static MetodoPagoSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (MetodoPagoSpRemoto) ContextUtil
				.lookup("java:MetodoPagoSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal i_modo = new BigDecimal(1);
		BigDecimal i_id_session = new BigDecimal(1);
		BigDecimal i_cod_procedimiento = new BigDecimal(1);
		BigDecimal i_nro_op = new BigDecimal(1);
		BigDecimal i_nro_factura = new BigDecimal(1);
		BigDecimal i_nro_pieza_dental = new BigDecimal(1);

		RsMetodoPago respuesta;
		try {
			respuesta = remoto.llamarMetodoPagoSp(i_modo, i_id_session,
					i_cod_procedimiento, i_nro_op, i_nro_factura,
					i_nro_pieza_dental);

			System.out.println("resp: " + respuesta.getGrabo());
			System.out.println(respuesta.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
