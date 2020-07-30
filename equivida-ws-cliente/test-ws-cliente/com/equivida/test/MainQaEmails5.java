package com.equivida.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.ibm.isd.QA_Equivida.SrvQaEmail.SrvQaEmailOutVar1;
import com.ibm.isd.QA_Equivida.SrvQaEmail.soapoverhttp.SrvQaEmail_PortType;
import com.ibm.isd.QA_Equivida.SrvQaEmail.soapoverhttp.SrvQaEmail_ServiceLocator;

public class MainQaEmails5 {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		// String address =
		// Parametros.getString("location.web.service.qa.emails");
		String address = "localhost";
		try {
			URL url = new URL(address);
			SrvQaEmail_ServiceLocator locator = new SrvQaEmail_ServiceLocator();

			SrvQaEmail_PortType service = locator.getSrvQaEmailSOAP(url);

			String em_email = "jose.perez@hotmail";
			SrvQaEmailOutVar1 resutl = service.QAEmailSrv(em_email);

			System.out.println("1" + resutl.getCountrydomain_emails());
			System.out.println("2" + resutl.getDomain_emails());
			System.out.println("3" + resutl.getEmail_emails());
			System.out.println("4" + resutl.getMessage());
			System.out.println("5" + resutl.getNameemail_emails());
			System.out.println("6" + resutl.getPrefixdomain_emails());
			System.out.println("7" + resutl);
			System.out.println("7" + resutl.getStatus());
			System.out.println("8" + resutl.getSuffixdomain_emails());
			System.out.println("9" + resutl.getUnhandledpattern_emails());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
