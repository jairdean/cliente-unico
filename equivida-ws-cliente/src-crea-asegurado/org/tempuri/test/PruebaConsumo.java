package org.tempuri.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.tempuri.WebService1Locator;
import org.tempuri.WebService1Soap;

public class PruebaConsumo {
	public static void main(String[] args) {
		// String address = "http://10.10.52.107/WSCreaAseg/WSCreaAseg.asmx";
		String address = "http://10.10.56.12/WSCreaAseg/WSCreaAseg.asmx";

		URL url;
		try {
			url = new URL(address);

			WebService1Locator locator = new WebService1Locator();

			WebService1Soap service = locator.getWebService1Soap(url);

			int idPersona = 8857206;
			if (args != null && args.length > 0) {
				idPersona = Integer.parseInt(args[0]);
			}

			System.out.println("idper:" + idPersona);

			String result = service.WSCreaAsegurado(8857206);

			int posIni = result.indexOf("<CodAseg>");
			posIni = posIni + "<CodAseg>".length();
			int posFin = result.indexOf("</CodAseg>");

			String a = result.substring(posIni, posFin);
			System.out.println("respuesta: " + a);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
