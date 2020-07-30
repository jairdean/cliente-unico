package com.equivida.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.ibm.isd.QA_Equivida.SrvQaNombres.SrvQaNombresOutVar1;
import com.ibm.isd.QA_Equivida.SrvQaNombres.soapoverhttp.SrvQaNombres_PortType;
import com.ibm.isd.QA_Equivida.SrvQaNombres.soapoverhttp.SrvQaNombres_ServiceLocator;

public class MainQaNombres4 {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		// String address =
		// Parametros.getString("location.web.service.qa.nombres");
		String address = "http://10.10.52.53:9080/wisd/QA_Equivida/SrvQaNombres";
		try {
			URL url = new URL(address);

			SrvQaNombres_ServiceLocator locator = new SrvQaNombres_ServiceLocator();

			SrvQaNombres_PortType service = locator.getSrvQaNombresSOAP(url);

			// String nombreIng = "CARLOS ALBERTO HIDALGO HERRERA";
			SrvQaNombresOutVar1 result = service.QANombresSrv("DANIELA ZURITA");

			System.out.println(result.getUnhandledpattern_nombres());
			System.out.println(result.getFirstname_nombres());
			System.out.println(result.getSecondname_nombres());
			System.out.println(result.getFirstlastname_nombres());
			System.out.println(result.getSecondlastname_nombres());
			System.out.println(result.getStatus());
			System.out.println(result.getMessage());
			System.out.println(result.getNameprefix_nombres());
			System.out.println(result.getValidacion());
			System.out.println(result.getGenero_nombres());
			// System.out.println("Entrada: " + nombreIng);
			System.out.println("Salida: " + result.getNombre());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}