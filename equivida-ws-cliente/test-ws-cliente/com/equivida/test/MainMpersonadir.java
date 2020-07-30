package com.equivida.test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.www.services.clienteequivida.mpersona_dir.ClienteequividaMpersonadirSrvLocator;
import com.equivida.www.services.clienteequivida.mpersona_dir.PortType;
import com.equivida.www.services.clienteequivida.mpersona_dir.Request;
import com.equivida.www.services.clienteequivida.mpersona_dir.Response;

public class MainMpersonadir {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		ClienteequividaMpersonadirSrvLocator locator = new ClienteequividaMpersonadirSrvLocator();

		PortType service = locator.getPort();

		int id_persona = 16;
		int cod_tipo_dir = 12;
		String txt_direccion = "El Condado";
		int cod_municipio = 1;
		int cod_dpto = 2;
		int cod_pais = 3;

		Request req = new Request(id_persona, cod_tipo_dir, txt_direccion,
				cod_municipio, cod_dpto, cod_pais);

		Response resp = service.invoke(req);

		System.out.println(resp.getCod_error());
		System.out.println(resp.getMsg_error());
		System.out.println(resp.getNum_filas());
	}
}
