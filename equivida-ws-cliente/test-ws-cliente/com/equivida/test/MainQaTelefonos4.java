package com.equivida.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.ibm.isd.QA_Equivida.SrvQaTelefonos.SrvQaTelefonosOutVar1;
import com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_PortType;
import com.ibm.isd.QA_Equivida.SrvQaTelefonos.soapoverhttp.SrvQaTelefonos_ServiceLocator;

public class MainQaTelefonos4 {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		// String address =
		// Parametros.getString("location.web.service.qa.nombres");
		String address = "localhost";
		try {
			URL url = new URL(address);

			SrvQaTelefonos_ServiceLocator locator = new SrvQaTelefonos_ServiceLocator();

			SrvQaTelefonos_PortType service = locator
					.getSrvQaTelefonosSOAP(url);

			String te_numero = "022494378";
			SrvQaTelefonosOutVar1 result = service.qaTelefonosSrv(te_numero);

			System.out.println("1" + result.getAreacode_telefonos());
			System.out.println("2" + result.getMensaje_telefonos());
			System.out.println("3" + result.getMessage());
			System.out.println("4" + result.getNumberphone_telefonos());
			System.out.println("5" + result.getStatus());
			System.out.println("6" + result.getUnhandledpattern_telefonos());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
