package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsCertificadoIndividual;
import com.equivida.sise.sp.CertificadoIndividualSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class CertificadoIndividualSpTest {

	static CertificadoIndividualSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (CertificadoIndividualSpRemoto) ContextUtil
				.lookup("java:CertificadoIndividualSp/remote");
	}

	@Test
	public void testLlamarSp() {

		BigDecimal cod_suc = BigDecimal.valueOf(1);
		BigDecimal cod_ramo = BigDecimal.valueOf(50);
		BigDecimal nro_pol = BigDecimal.valueOf(1500001125);
		BigDecimal nro_aseg = BigDecimal.valueOf(158);
		String nro_doc = "";
		String campoin1 = "";
		String campoin2 = "";
		String campoin3 = "";
		String campoin4 = "";
		String campoin5 = "";

		try {
			List<RsCertificadoIndividual> respuestaLista = remoto
					.llamarCertificadoIndividualSp(cod_suc, cod_ramo, nro_pol,
							nro_aseg, nro_doc, campoin1, campoin2, campoin3,
							campoin4, campoin5);

			for (RsCertificadoIndividual respuesta : respuestaLista) {

				System.out.println("resp: consulta poliza "
						+ respuesta.getNombre());
				System.out.println(respuesta.getApellido1());
				System.out.println(respuesta.getCobertura());
				System.out.println(respuesta.getNroDocumento());
				System.out.println(respuesta.getNroPoliza());
				System.out.println(respuesta.getVigenciaHasta());
				System.out.println(respuesta.getCodRamo());

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
