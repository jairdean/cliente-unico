package com.equivida.sise.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.rs.RsConsultaPolizas;
import com.equivida.sise.sp.ConsultaPolizasSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class ConsultaPolizaSpTest {

	static ConsultaPolizasSpRemoto remoto;

	@Before
	public void setUp() throws Exception {
		remoto = (ConsultaPolizasSpRemoto) ContextUtil
				.lookup("java:ConsultaPolizasSp/remote");
	}

	@Test
	public void testLlamarSp() {
		BigDecimal id_persona = BigDecimal.valueOf(1);
		String num_doc = "1";
		Integer sn_activas = 1;
		Integer tipo_busca = 1;
		String campo1 = "";
		String campo2 = "";
		String campo3 = "";
		String campo4 = "";
		String campo5 = "";

		List<RsConsultaPolizas> respuesta;
		try {
			respuesta = remoto.llamarConsultaPolizasSp(id_persona, num_doc,
					sn_activas, tipo_busca, campo1, campo2, campo3, campo4,
					campo5);

			for (RsConsultaPolizas rsConsultaPolizas : respuesta) {

				System.out.println("resp: consulta poliza "
						+ rsConsultaPolizas.getEstadoPoliza());
				System.out.println(rsConsultaPolizas.getNumeroPoliza());
				System.out.println(rsConsultaPolizas.getCodigoSucursal());
				System.out.println(rsConsultaPolizas.getCodigoRamo());
				System.out.println(rsConsultaPolizas.getFechaDesde());
				System.out.println(rsConsultaPolizas.getFechaHasta());
				System.out.println(rsConsultaPolizas.getCodigoTipoAgente());
				System.out.println(rsConsultaPolizas.getCodigoAgente());
				System.out.println(rsConsultaPolizas.getNombreAgente());
				System.out.println(rsConsultaPolizas.getCodigoTipoPersona());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
