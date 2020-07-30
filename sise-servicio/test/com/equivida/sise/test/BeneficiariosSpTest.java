package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsBeneficiarios;
import com.equivida.sise.sp.BeneficiariosSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class BeneficiariosSpTest {

	private BeneficiariosSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (BeneficiariosSpRemoto) ContextUtil
				.lookup("java:BeneficiariosSp/remote");
	}

	@Test
	public void testLlamarSp() {

		BigDecimal cod_suc = new BigDecimal(1);
		BigDecimal cod_ramo = new BigDecimal(59);
		BigDecimal nro_pol = new BigDecimal(1590000869);
		BigDecimal cod_aseg = new BigDecimal(1554280);
		String campoin1 = "";
		String campoin2 = "";
		String campoin3 = "";
		String campoin4 = "";
		String campoin5 = "";

		List<RsBeneficiarios> respuesta;
		try {
			respuesta = remoto.llamarBeneficiariosSp(cod_suc, cod_ramo,
					nro_pol, cod_aseg, campoin1, campoin2, campoin3, campoin4,
					campoin5);

			for (RsBeneficiarios rsEstadoCuenta : respuesta) {

				System.out.println(rsEstadoCuenta.getNombre());
				System.out.println(rsEstadoCuenta.getApellido1());
				System.out.println(rsEstadoCuenta.getNroBeneficiario());
				System.out.println(rsEstadoCuenta.getNroDocumento());
				System.out.println(rsEstadoCuenta.getTipoDocumento());
				System.out.println(rsEstadoCuenta.getParentesco());
				System.out.println(rsEstadoCuenta.getNroAsegurado());
				System.out.println(rsEstadoCuenta.getCodDocumento());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
