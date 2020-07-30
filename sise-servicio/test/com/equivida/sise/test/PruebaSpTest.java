package com.equivida.sise.test;

import org.junit.Before;
import org.junit.Test;

import com.equivida.sise.sp.PruebaSpRemoto;
import com.equivida.sise.test.util.ContextUtil;

public class PruebaSpTest {

	private PruebaSpRemoto pruebaSp;

	@Before
	public void setUp() throws Exception {
		pruebaSp = (PruebaSpRemoto) ContextUtil.lookup("java:PruebaSp/remote");
	}

	@Test
	public void testLlamarPrueba() {
		pruebaSp.llamarPrueba();
	}
	
	@Test
	public void testInsertDato() {
		pruebaSp.insertDatos("CÁRDENASÑ");
	}

}
