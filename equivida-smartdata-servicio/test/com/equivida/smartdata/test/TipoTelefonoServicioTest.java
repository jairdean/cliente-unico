package com.equivida.smartdata.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.equivida.smartdata.model.TipoTelefonoSd;
import com.equivida.smartdata.servicio.TipoTelefonoServicioSdRemoto;

public class TipoTelefonoServicioTest {

	private TipoTelefonoServicioSdRemoto servicio;

	@Before
	public void setUp() throws Exception {
		servicio = (TipoTelefonoServicioSdRemoto) ContextUtil.lookup("java:TipoTelefonoServicio/remote");
	}

	@Test
	public void consultaTodos() {
		System.out.println("conuslta");
		List<TipoTelefonoSd> tipos = servicio.findAll();

		for (TipoTelefonoSd t : tipos) {
			System.out.println("t: " + t.getTipoTelefono());
		}
	}
}