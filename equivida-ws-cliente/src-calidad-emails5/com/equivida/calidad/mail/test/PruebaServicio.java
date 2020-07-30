package com.equivida.calidad.mail.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.ibm.isd.QA_Equivida.SrvQaEmail.SrvQaEmailOutVar1;
import com.ibm.isd.QA_Equivida.SrvQaEmail.soapoverhttp.SrvQaEmail_PortType;
import com.ibm.isd.QA_Equivida.SrvQaEmail.soapoverhttp.SrvQaEmail_ServiceLocator;

public class PruebaServicio {
	public static void main(String[] args) throws ServiceException,
			RemoteException {
		boolean valid = false;

		String address = "http://10.10.52.53:9080/wisd/QA_Equivida/SrvQaEmail";
		URL url;
		try {
			url = new URL(address);

			SrvQaEmail_ServiceLocator locator = new SrvQaEmail_ServiceLocator();

			SrvQaEmail_PortType service = locator.getSrvQaEmailSOAP(url);

			SrvQaEmailOutVar1 result = service
					.QAEmailSrv("juan.ochoa@saviasoft.com");

			System.out.println("1" + result.getCountrydomain_emails());
			System.out.println("2" + result.getDomain_emails());
			System.out.println("3" + result.getEmail_emails());
			System.out.println("4" + result.getMessage());
			System.out.println("5" + result.getNameemail_emails());
			System.out.println("6" + result.getPrefixdomain_emails());
			System.out.println("7" + result.getStatus());
			System.out.println("8" + result.getSuffixdomain_emails());
			System.out.println("9" + result.getUnhandledpattern_emails());

			if (result.getUnhandledpattern_emails() == null
					&& result.getMessage() == null) {
				valid = true;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		System.out.println(valid);
	}
}
