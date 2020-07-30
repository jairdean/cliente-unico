package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsInfoSaldosPolizas;
import com.equivida.sise.sp.InfoSaldosPolizasSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class InfoSaldosPolizasSpTest {

	static InfoSaldosPolizasSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (InfoSaldosPolizasSpRemoto) ContextUtil
				.lookup("java:InfoSaldosPolizasSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal cod_suc = new BigDecimal(1);
		BigDecimal cod_ramo = new BigDecimal(59);
		BigDecimal nro_pol = new BigDecimal(1590000869);
		String campoin1 = "1";
		String campoin2 = "1";
		String campoin3 = "1";
		String campoin4 = "1";
		String campoin5 = "1";

		List<RsInfoSaldosPolizas> respuesta;
		try {
			respuesta = remoto.llamarInfoSaldosPolizasSp(cod_suc, cod_ramo,
					nro_pol, campoin1, campoin2, campoin3, campoin4, campoin5);

			for (RsInfoSaldosPolizas rsCuotasPrestamo : respuesta) {

				System.out.println("resp: -----------------"
						+ rsCuotasPrestamo.getCodRamo());
				System.out.println(rsCuotasPrestamo.getCodRamo());
				System.out.println(rsCuotasPrestamo.getNroCuentasMora());
				System.out.println(rsCuotasPrestamo.getNroPoliza());
				System.out.println(rsCuotasPrestamo.getPeriocidadPago());
				System.out.println(rsCuotasPrestamo.getValorAnual());
				System.out.println(rsCuotasPrestamo.getValorMora());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
