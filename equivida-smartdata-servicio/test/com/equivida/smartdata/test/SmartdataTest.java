package com.equivida.smartdata.test;

import org.junit.Before;
import org.junit.Test;

import com.equivida.smartdata.exception.SmartdataException;
import com.equivida.smartdata.servicio.SmartDataServicioSdRemote;

public class SmartdataTest {
	SmartDataServicioSdRemote servicio;

	@Before
	public void setUp() throws Exception {
		servicio = (SmartDataServicioSdRemote) ContextUtil.lookup("java:SmartDataServicio/remote");
	}

	@Test
	public void consultaEnDataBookPersisteSmartData() {
		System.out.println("consultaEnDataBookPersisteSmartData");
		try {
			servicio.consultaEnDataBookPersisteSmartData("1716757321", "juanochoa");
		} catch (SmartdataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
