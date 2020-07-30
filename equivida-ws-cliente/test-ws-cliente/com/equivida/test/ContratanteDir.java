package com.equivida.test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.sise.ws.client.InsertDireccionContratante;
import com.equivida.sise.ws.client.InsertDireccionContratanteWs;
import com.equivida.sise.ws.client.impl.InsertDireccionContratanteWsImplServiceLocator;

public class ContratanteDir {
	public static void main(String[] args) throws ServiceException, RemoteException {
		System.out.println("-----------------------1");
		String address = "http://localhost:8080/sise-servicio-1.0/InsertDireccionContratanteWsImpl";
		URL url;
		try {
			url = new URL(address);

			System.out.println("-----------------------2");
			InsertDireccionContratanteWsImplServiceLocator locator = new InsertDireccionContratanteWsImplServiceLocator();
			InsertDireccionContratanteWs servicio = locator.getInsertDireccionContratanteWsImplPort(url);

			System.out.println("-----------------------3");
			System.out.println("-----------------------4");

			System.out.println("=================================ANTES DE CONSUMIR");

			InsertDireccionContratante response = servicio.spi_mpersona_dir_wkf(BigDecimal.ONE, BigDecimal.ONE,
					"prueba", BigDecimal.valueOf(15), BigDecimal.valueOf(17), BigDecimal.valueOf(1));

			System.out.println("=================================CONSUMIDO");

			System.out.println("getMessage_wkf:" + response.getMessage_wkf());
			System.out.println("getCod_error:" + response.getCod_error());
			System.out.println("Id_mpersona_dir_out:" + response.getId_mpersona_dir_out());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
