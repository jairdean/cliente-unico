package com.equivida.test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.www.services.clienteequivida.mpersona_telef.ClienteequividaMpersona_telefSrvLocator;
import com.equivida.www.services.clienteequivida.mpersona_telef.PortType;
import com.equivida.www.services.clienteequivida.mpersona_telef.Request;
import com.equivida.www.services.clienteequivida.mpersona_telef.Response;

public class MainMpersonatelef {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		ClienteequividaMpersona_telefSrvLocator locator = new ClienteequividaMpersona_telefSrvLocator();

		PortType service = locator.getPort();

		int id_persona = 16;
		int cod_tipo_telef = 12;
		String txt_telefono = "022805393";

		Request req = new Request(id_persona, cod_tipo_telef, txt_telefono);

		Response resp = service.invoke(req);

		System.out.println(resp.getCod_error());
		System.out.println(resp.getMsg_error());
		System.out.println(resp.getNum_filas());
	}
}