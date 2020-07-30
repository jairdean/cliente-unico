package com.equivida.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.sise.ws.client.CumulosDePagoWs;
import com.equivida.sise.ws.client.RsCumulosDePago;
import com.equivida.sise.ws.client.impl.CumulosDePagoWsImplServiceLocator;

public class MainCumuloPago {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		String cedula = "0923069165";

		CumulosDePagoWsImplServiceLocator locator = new CumulosDePagoWsImplServiceLocator();

		String address = "http://localhost:8080/sise-servicio-1.0/CumulosDePagoWsImpl";
		URL url;

		try {
			url = new URL(address);

			CumulosDePagoWs servicio = locator.getCumulosDePagoWsImplPort(url);

			RsCumulosDePago[] repuestaLista = servicio.consultar(cedula);

			for (RsCumulosDePago rsCumulosDePago : repuestaLista) {
				System.out.println(rsCumulosDePago.getPoliza());
				System.out.println(rsCumulosDePago.getEstado());
				System.out.println(rsCumulosDePago.getMontoVida());
				System.out.println(rsCumulosDePago.getMontoMyda());
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
