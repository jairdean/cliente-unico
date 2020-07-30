package com.equivida.test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.sise.ws.client.InsertTelefonoContratante;
import com.equivida.sise.ws.client.InsertTelefonoContratanteWs;
import com.equivida.sise.ws.client.impl.InsertTelefonoContratanteWsImplServiceLocator;

public class ContratanteTelf {
	public static void main(String[] args) throws ServiceException, RemoteException {
		System.out.println("-----------------------1");
		String address = "http://localhost:8080/sise-servicio-1.0/InsertTelefonoContratanteWsImpl";
		URL url;
		try {
			url = new URL(address);

			System.out.println("-----------------------2");
			InsertTelefonoContratanteWsImplServiceLocator locator = new InsertTelefonoContratanteWsImplServiceLocator();
			InsertTelefonoContratanteWs servicio = locator.getInsertTelefonoContratanteWsImplPort(url);

			System.out.println("-----------------------3");
			System.out.println("-----------------------4");

			System.out.println("=================================ANTES DE CONSUMIR");

			InsertTelefonoContratante response = servicio.spi_mpersona_telef_wkf(BigDecimal.ONE, BigDecimal.ONE,
					"prueba telf");

			System.out.println("=================================CONSUMIDO");

			System.out.println("getMessage_wkf:" + response.getMessage_wkf());
			System.out.println("getCod_error:" + response.getCod_error());
			System.out.println("getId_mper_telef_cod_out:" + response.getId_mper_telef_cod_out());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
