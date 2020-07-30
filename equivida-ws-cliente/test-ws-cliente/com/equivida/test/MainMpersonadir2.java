package com.equivida.test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.sise.ws.client.InsertDireccionPersona;
import com.equivida.sise.ws.client.InsertDireccionPersonaWs;
import com.equivida.sise.ws.client.impl.InsertDireccionPersonaWsImplServiceLocator;

public class MainMpersonadir2 {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		BigDecimal id_persona = new BigDecimal(16);
		BigDecimal cod_tipo_dir = new BigDecimal(12);
		String txt_direccion = "El Condado";
		String txt_edificio = "Edificion";
		String txt_urbanizacion = "Edificion";
		String txt_sector = "Edificion";
		String campoin1 = "Edificion";
		String campoin2 = "Edificion";
		String campoin3 = "Edificion";
		String campoin4 = "Edificion";
		String campoin5 = "Edificion";
		BigDecimal cod_municipio = new BigDecimal(1);
		BigDecimal cod_dpto = new BigDecimal(2);
		BigDecimal cod_pais = new BigDecimal(3);

		InsertDireccionPersonaWsImplServiceLocator locator = new InsertDireccionPersonaWsImplServiceLocator();

		String address = "http://localhost:8080/sise-servicio-1.0/InsertDireccionPersonaWsImpl";
		URL url;

		try {
			url = new URL(address);

			InsertDireccionPersonaWs servicio = locator
					.getInsertDireccionPersonaWsImplPort(url);

			InsertDireccionPersona repuesta = servicio
					.ws_sise_insert_mpersonadir(id_persona, cod_tipo_dir,
							txt_direccion, cod_municipio, cod_dpto, cod_pais,
							txt_edificio, txt_urbanizacion, txt_sector,
							campoin1, campoin2, campoin3, campoin4, campoin5);

			System.out.println("ID:" + repuesta.getIdDireccion());
			System.out.println("error:" + repuesta.getCodError());
			System.out.println("merror:" + repuesta.getMsgError());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
