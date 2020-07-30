package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsInfoPoliza;
import com.equivida.sise.sp.InfoPolizaSpRemote;
import com.equivida.sise.test.util.ContextUtil;

public class InfoPolizaSpTest {

	private InfoPolizaSpRemote remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (InfoPolizaSpRemote) ContextUtil
				.lookup("java:InfoPolizaSp/remote");
	}

	@Test
	public void testLlamarSp() {

		BigDecimal cod_suc = new BigDecimal(1);
		BigDecimal cod_ramo = new BigDecimal(59);
		BigDecimal nro_pol = new BigDecimal("1590000869");
		String campoin1 = "4";
		String campoin2 = "4";
		String campoin3 = "4";
		String campoin4 = "4";
		String campoin5 = "5";

		List<RsInfoPoliza> respuesta;
		try {
			respuesta = remoto.llamarInfoPolizaSp(cod_suc, cod_ramo, nro_pol,
					campoin1, campoin2, campoin3, campoin4, campoin5);

			for (RsInfoPoliza rsCuotasPrestamo : respuesta) {

				System.out.println("resp: -----------------"
						+ rsCuotasPrestamo.getDescRamo());
				System.out.println(rsCuotasPrestamo.getNumeroPoliza());
				System.out.println(rsCuotasPrestamo.getInicioVigencia());
				System.out.println(rsCuotasPrestamo.getFinVigencia());
				System.out.println(rsCuotasPrestamo.getNombreContratante());
				System.out.println(rsCuotasPrestamo.getImpValorAseg());
				System.out.println(rsCuotasPrestamo.getOcupacion());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
