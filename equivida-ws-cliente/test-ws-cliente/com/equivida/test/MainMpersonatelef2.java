package com.equivida.test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.sise.ws.client.InsertTelefonoPersona;
import com.equivida.sise.ws.client.InsertTelefonoPersonaWs;
import com.equivida.sise.ws.client.impl.InsertTelefonoPersonaWsImplServiceLocator;

public class MainMpersonatelef2 {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		BigDecimal id_persona = BigDecimal.valueOf(2755807);
		BigDecimal cod_tipo_telef = BigDecimal.valueOf(12);
		String txt_telefono = "022805393";
		String campoin1 = null, campoin2 = null, campoin3 = null, campoin4 = null, campoin5 = null;

		InsertTelefonoPersonaWsImplServiceLocator locator = new InsertTelefonoPersonaWsImplServiceLocator();

		String address = "http://localhost:8080/sise-servicio/InsertTelefonoPersonaWsImpl";
		URL url;
		try {
			url = new URL(address);

			InsertTelefonoPersonaWs servicio = locator
					.getInsertTelefonoPersonaWsImplPort(url);

			InsertTelefonoPersona repuesta= servicio.ws_sise_insert_mpersona_telef(id_persona, cod_tipo_telef,
					txt_telefono, campoin1, campoin2, campoin3, campoin4,
					campoin5);
			
			System.out.println("ID:"+repuesta.getIdTelefono());
			System.out.println("error:"+repuesta.getCodError());
			System.out.println("merror:"+repuesta.getMsgError());
			

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}