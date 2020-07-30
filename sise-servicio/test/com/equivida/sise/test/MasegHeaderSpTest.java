package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.MasegHeader;
import com.equivida.sise.sp.MasegHeaderSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class MasegHeaderSpTest {

	private MasegHeaderSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (MasegHeaderSpRemoto) ContextUtil
				.lookup("MasegHeaderSp/remote");
	}

	@Test
	public void testLlamarSp() {

		BigDecimal idPersona = new BigDecimal(39036);
		BigDecimal codFiguraAseg = new BigDecimal(6);
		BigDecimal codTipoAseg = new BigDecimal(6);
		BigDecimal codImpAseg = new BigDecimal(6);
		BigDecimal codTipoAgente = new BigDecimal(1);
		BigDecimal codAgente = new BigDecimal(6);
		Timestamp fechaAlta = new Timestamp(1);
		Timestamp fechaBaja = new Timestamp(1);
		BigDecimal codigoOcupacion = new BigDecimal(1);
		Integer avisoVto = -1;
		String codAsegVinc = "5";
		Timestamp fechaUltMod = new Timestamp(1);
		String codUsuario = "5";
		String nombFactura = "5";
		BigDecimal tazaFianzas = new BigDecimal(1);
		Integer consorcio = -1;
		BigDecimal codMoneda = new BigDecimal(1);
		BigDecimal impSueldo = new BigDecimal(1);
		BigDecimal codDeporte = new BigDecimal(1);
		String edificio = "5";
		String urbanizacion = "5";
		String sector = "5";
		String nombresConyuge = "5";
		String apellidoConyuge = "5";
		BigDecimal codTipoDocConyuge = new BigDecimal(1);
		String numeroDocConyuge = "5";
		String campoIn1 = "5";
		String campoIn2 = "5";
		String campoIn3 = "5";
		String campoIn4 = "5";
		String campoIn5 = "5";

		MasegHeader respuesta;
		try {
			respuesta = remoto.llamarMasegHeaderSp(idPersona, codFiguraAseg,
					codTipoAseg, codImpAseg, codTipoAgente, codAgente,
					fechaAlta, fechaBaja, codigoOcupacion, avisoVto,
					codAsegVinc, fechaUltMod, codUsuario, nombFactura,
					tazaFianzas, consorcio, codMoneda, impSueldo, codDeporte,
					edificio, urbanizacion, sector, nombresConyuge,
					apellidoConyuge, codTipoDocConyuge, numeroDocConyuge,
					campoIn1, campoIn2, campoIn3, campoIn4, campoIn5);

			System.out.println("resp: -----------------"
					+ respuesta.getCodError());
			System.out.println(respuesta.getMsgError());
			System.out.println(respuesta.getNumFilas());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
