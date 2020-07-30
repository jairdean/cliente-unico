package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsRegistroPresupuestoHeader;
import com.equivida.sise.sp.RegistroPresupuestoHeaderSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class RegistroPresupuestoHeaderSpTest {

	static RegistroPresupuestoHeaderSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (RegistroPresupuestoHeaderSpRemoto) ContextUtil
				.lookup("java:RegistroPresupuestoHeaderSp/remote");
	}

	@Test
	public void testLlamarSp() {

		BigDecimal i_imp_id_sesion = new BigDecimal(29971);
		BigDecimal i_imp_nro_pol = new BigDecimal(1250001356);
		BigDecimal i_nro_aseg = new BigDecimal(5921);
		BigDecimal i_nro_pariente = new BigDecimal(0);
		BigDecimal i_imp_tipo_identifaseg = new BigDecimal(1);
		String i_txt_cedula = "1713470704";
		BigDecimal i_imp_cod_plan = new BigDecimal(29);
		BigDecimal i_imp_cod_red = new BigDecimal(1);
		BigDecimal i_imp_tipo_identifpres = new BigDecimal("1707041184001");
		String i_txt_identifpres = "1";
		BigDecimal i_imp_cod_suc = new BigDecimal(22559);
		BigDecimal i_imp_tipo_identifmdiag = new BigDecimal(1);
		String i_txt_identifmdiag = "ANODONCIA";
		String i_tipo_reg = "";

		RsRegistroPresupuestoHeader respuesta;
		try {
			respuesta = remoto.llamarRegistroPresupuestoHeaderSp(
					i_imp_id_sesion, i_imp_nro_pol, i_nro_aseg, i_nro_pariente,
					i_imp_tipo_identifaseg, i_txt_cedula, i_imp_cod_plan,
					i_imp_cod_red, i_imp_tipo_identifpres, i_txt_identifpres,
					i_imp_cod_suc, i_imp_tipo_identifmdiag, i_txt_identifmdiag,
					i_tipo_reg);

			System.out.println("resp: " + respuesta.getGrabo());
			System.out.println(respuesta.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
