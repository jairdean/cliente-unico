package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsIngresoSucursales;
import com.equivida.sise.sp.IngresoSucursalesSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class IngresoSucursalSpTest {

	static IngresoSucursalesSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (IngresoSucursalesSpRemoto) ContextUtil
				.lookup("java:IngresoSucursalesSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal codPres = new BigDecimal(0);
		String sucursal = "Principal";
		String direccion = "GASPAR DE VILLAROEL Y AMAZONAS 1440";
		String telefono = "634534534";
		BigDecimal ubicacion = new BigDecimal(9);
		BigDecimal municipio = new BigDecimal(1701);
		BigDecimal departamento = new BigDecimal(17);
		BigDecimal modo = new BigDecimal(0);
		BigDecimal codSucursal = new BigDecimal(272);
		BigDecimal tipoIdPres = new BigDecimal(2);
		String nroIdPress = "171655465444";

		try {
			RsIngresoSucursales respuestaLista = remoto
					.llamarIngresSucuralesSp(codPres, sucursal, direccion,
							telefono, ubicacion, municipio, departamento, modo,
							codSucursal, tipoIdPres, nroIdPress);

			System.out.println("resp: consulta poliza "
					+ respuestaLista.getMessage());
			System.out.println(respuestaLista.getGrabo());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
