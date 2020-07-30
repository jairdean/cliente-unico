package com.equivida.sise.test;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.sp.RegistroPresupuestoSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class RegistroPresupuestoSpTest {

	static RegistroPresupuestoSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (RegistroPresupuestoSpRemoto) ContextUtil.lookup("java:RegistroPresupuestoSp/remote");
	}

	@Test
	public void testLlamarSp() {
//		BigDecimal i_imp_id_sesion = new BigDecimal(1);
//		BigDecimal i_imp_cod_procedimiento = new BigDecimal(1);
//		BigDecimal i_cnt_cantidad = new BigDecimal(1);
//		String i_cod_diagnostico = "1";
//		BigDecimal i_tipo_identif_medico = new BigDecimal(1);
//		String i_identif_medico = "2";
//		BigDecimal i_cod_procedimiento_baja = new BigDecimal(1);
//		BigDecimal i_nro_pieza_dental = new BigDecimal(1);
//
//		RsRegistroPresupuesto respuesta;
		/*
		 * try { respuesta = remoto.llamarRegistroPresupuestoSp(i_imp_id_sesion,
		 * i_imp_cod_procedimiento, i_cnt_cantidad, i_cod_diagnostico,
		 * i_tipo_identif_medico, i_identif_medico, i_cod_procedimiento_baja,
		 * i_nro_pieza_dental);
		 * 
		 * System.out.println("resp: " + respuesta.getGrabo());
		 * System.out.println(respuesta.getMensaje());
		 * System.out.println(respuesta.getIdSesion());
		 * System.out.println(respuesta.getCodProcedimiento());
		 * System.out.println(respuesta.getCantidad());
		 * System.out.println(respuesta.getDiagnostico());
		 * System.out.println(respuesta.getCobertura());
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}
}
