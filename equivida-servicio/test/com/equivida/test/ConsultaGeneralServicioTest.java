package com.equivida.test;

import org.junit.BeforeClass;
import org.junit.Test;

import com.equivida.dto.RespuestaGeneralDto;
import com.equivida.servicio.ConsultaGeneralCUServicioRemoto;
import com.equivida.test.util.ContextUtil;

public class ConsultaGeneralServicioTest {

	static ConsultaGeneralCUServicioRemoto servicioRemoto;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		servicioRemoto = (ConsultaGeneralCUServicioRemoto) ContextUtil.lookup("java:ConsultaGeneralCUServicio/remote");
	}

	@Test
	public void testCrearSolicitudPersonaNatural() {
		RespuestaGeneralDto resp = servicioRemoto.consultaGenerica("1712715497", "prueba", null);

		System.out.println("resp: " + resp.getLugarEncuentra());
		System.out.println("resp: " + resp.getMensaje());
	}

}