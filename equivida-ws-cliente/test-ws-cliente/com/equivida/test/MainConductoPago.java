package com.equivida.test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.sise.ws.client.ConductoPagoWs;
import com.equivida.sise.ws.client.RsConductoPago;
import com.equivida.sise.ws.client.impl.ConductaPagoWsImplServiceLocator;

public class MainConductoPago {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		BigDecimal id_persona = BigDecimal.valueOf(2696277);

		ConductaPagoWsImplServiceLocator locator = new ConductaPagoWsImplServiceLocator();

		String address = "http://10.10.30.41:8080/sise-servicio-1.0/ConductaPagoWsImpl";
		URL url;

		try {
			url = new URL(address);

			ConductoPagoWs servicio = locator.getConductaPagoWsImplPort(url);

			RsConductoPago[] repuestaLista = servicio
					.ws_sise_lista_conductos(id_persona);

			for (RsConductoPago repuesta : repuestaLista) {
				System.out.println("ID:" + repuesta.getConductoPago());
				System.out.println("error:" + repuesta.getEstado());
				System.out.println("merror:" + repuesta.getTipoCuenta());
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
