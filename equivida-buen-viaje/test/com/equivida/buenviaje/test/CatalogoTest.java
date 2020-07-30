package com.equivida.buenviaje.test;

import org.junit.BeforeClass;
import org.junit.Test;

import com.equivida.buenviaje.dto.Catalogo;
import com.equivida.buenviaje.dto.RespuestaCatalogo;
import com.equivida.buenviaje.ws.CatalogoServicioRemoto;

public class CatalogoTest {
	static CatalogoServicioRemoto servicioRemoto;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		servicioRemoto = (CatalogoServicioRemoto) ContextUtil
				.lookup("java:CatalogoServicio/remote");
	}

	@Test
	public void test() {
		RespuestaCatalogo lista = servicioRemoto.consultarPaises();
		imprimirCatalogo(lista);
	}

	private void imprimirCatalogo(RespuestaCatalogo lista) {
		for (Catalogo c : lista.getCatalogoLista()) {
			System.out.println("Codigo: " + c.getCodigo() + " Nombre: "
					+ c.getNombre());
		}
	}
}
