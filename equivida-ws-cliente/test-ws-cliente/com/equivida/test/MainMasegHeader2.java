package com.equivida.test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.sise.ws.client.MasegHeader;
import com.equivida.sise.ws.client.MasegHeaderWs;
import com.equivida.sise.ws.client.impl.MasegHeaderWsImplServiceLocator;

public class MainMasegHeader2 {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		BigDecimal idPersona = new BigDecimal(39036);
		BigDecimal codFiguraAseg = new BigDecimal(6);
		BigDecimal codTipoAseg = new BigDecimal(6);
		BigDecimal codImpAseg = new BigDecimal(6);
		BigDecimal codTipoAgente = new BigDecimal(1);
		BigDecimal codAgente = new BigDecimal(6);
		String fechaAlta = "2012/08/08";
		String fechaBaja = null;
		BigDecimal codigoOcupacion = new BigDecimal(1);
		Integer avisoVto = -1;
		String codAsegVinc = "5";
		String fechaUltMod = "2012/08/08";
		String codUsuario = "5";
		String nombFactura = "5";
		BigDecimal tazaFianzas = new BigDecimal(1);
		Integer consorcio = -1;
		BigDecimal codMoneda = new BigDecimal(1);
		BigDecimal impSueldo = new BigDecimal(1);
		BigDecimal codDeporte = new BigDecimal(1);
		String edificio = "5";
		String urbanizacion = "5";
		String sector = "5";
		String nombresConyuge = null;
		String apellidoConyuge = null;
		BigDecimal codTipoDocConyuge = BigDecimal.valueOf(1);
		String numeroDocConyuge = null;
		String campoIn1 = null;
		String campoIn2 = null;
		String campoIn3 = null;
		String campoIn4 = null;
		String campoIn5 = null;

		MasegHeaderWsImplServiceLocator locator = new MasegHeaderWsImplServiceLocator();

		String address = "http://yacutingadevelop.equivida.local:8080/sise-servicio-1.0/MasegHeaderWsImpl";
		URL url;

		try {
			url = new URL(address);

			MasegHeaderWs servicio = locator.getMasegHeaderWsImplPort(url);

			MasegHeader repuesta = servicio.ws_sise_insert_maseg_header_edm(
					idPersona, codFiguraAseg, codTipoAseg, codImpAseg,
					codTipoAgente, codAgente, fechaAlta, fechaBaja,
					codigoOcupacion, avisoVto, codAsegVinc, fechaUltMod,
					codUsuario, nombFactura, tazaFianzas, consorcio, codMoneda,
					impSueldo, codDeporte, edificio, urbanizacion, sector,
					nombresConyuge, apellidoConyuge, codTipoDocConyuge,
					numeroDocConyuge, campoIn1, campoIn2, campoIn3, campoIn4,
					campoIn5);

			System.out.println("ID:" + repuesta.getCodAseg());
			System.out.println("error:" + repuesta.getCodError());
			System.out.println("merror:" + repuesta.getMsgError());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
