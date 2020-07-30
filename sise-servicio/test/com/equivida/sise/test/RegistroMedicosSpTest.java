package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsRegistroMedicos;
import com.equivida.sise.sp.RegistroMedicosSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class RegistroMedicosSpTest {

	static RegistroMedicosSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (RegistroMedicosSpRemoto) ContextUtil
				.lookup("java:RegistroMedicosSp/remote");
	}

	@Test
	public void testLlamarSp() {

		BigDecimal i_imp_tipoid_prestador = new BigDecimal(1);
		String i_txt_nro_iden_prestador = "1";
		BigDecimal i_imp_tipo_iden_medico = new BigDecimal(1);
		String i_txt_nro_iden_medico = "1";
		String i_txt_licencia = "1";
		String i_txt_apellido1_medico = "1";
		String i_txt_apellido2_medico = "1";
		String i_txt_nombres_medico = "1";
		String i_txt_mail_medico = "1";
		String i_txt_telef_conv = "1";
		String i_txt_telef_cel = "1";
		BigDecimal i_imp_operadora_cel = new BigDecimal(1);
		BigDecimal i_cod_suc = null;

		RsRegistroMedicos respuesta;
		try {
			respuesta = remoto.llamarRegistroMedicosSp(i_imp_tipoid_prestador,
					i_txt_nro_iden_prestador, i_imp_tipo_iden_medico,
					i_txt_nro_iden_medico, i_txt_licencia,
					i_txt_apellido1_medico, i_txt_apellido2_medico,
					i_txt_nombres_medico, i_txt_mail_medico, i_txt_telef_conv,
					i_txt_telef_cel, i_imp_operadora_cel, i_cod_suc);

			System.out.println("resp: " + respuesta.getGrabo());
			System.out.println(respuesta.getMessage());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
