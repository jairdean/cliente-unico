package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsMetodoBonificacion;
import com.equivida.sise.sp.MetodoBonificacionSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class MetodoBonificacionSpTest {

	static MetodoBonificacionSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (MetodoBonificacionSpRemoto) ContextUtil
				.lookup("java:MetodoBonificacionSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal i_modo = new BigDecimal(2);
		BigDecimal i_id_session = new BigDecimal(90026);
		BigDecimal i_cod_procedimiento = new BigDecimal(2005);
		BigDecimal i_cod_tipo_doc = new BigDecimal(1);
		String i_nro_doc = "1710441443";
		BigDecimal i_nro_op = new BigDecimal(121367);
		BigDecimal i_nro_pieza_dental = new BigDecimal(65);

		RsMetodoBonificacion respuesta;
		try {
			respuesta = remoto.llamarMetodoBonificacionSp(i_modo, i_id_session,
					i_cod_procedimiento, i_cod_tipo_doc, i_nro_doc, i_nro_op,
					i_nro_pieza_dental);
			
			System.out.println(">>>>>  "+respuesta.getMsgError());
			System.out.println(">>>>>  "+respuesta.getCantidad());
			System.out.println(">>>>>  "+respuesta.getCobertura());
			System.out.println(">>>>>  "+respuesta.getCoPago());
			System.out.println(">>>>>  "+respuesta.getRecalculoPresupuesto());
			System.out.println(">>>>>  "+respuesta.getIdDiagnostico());
			System.out.println(">>>>>  "+respuesta.getFechaVencimientoCarencia());
			System.out.println(">>>>>  "+respuesta.getValorCubierto());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
