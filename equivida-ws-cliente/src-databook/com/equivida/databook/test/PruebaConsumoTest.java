package com.equivida.databook.test;

import com.equivida.databook.client.DatabookService;
import com.equivida.databook.client.impl.DatabookServiceImpl;
import com.equivida.databook.exception.DatabookException;
import com.equivida.databook.model.Registros;

public class PruebaConsumoTest {
	public static void main(String[] args) throws DatabookException {
		System.out.println("inicio");

		DatabookService dbs = new DatabookServiceImpl("http://playavip.com.ec/equividawebservices.php", "0703445692",
				"EQUIVIDA");
		Registros registros = dbs.consultaDatabook();

		System.out.println("salida 2: " + registros.getSri().getProvincia());
	}

}
