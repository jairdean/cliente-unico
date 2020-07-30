package com.equivida.test;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import com.equivida.www.services.clienteequivida.maseg_header.ClienteequividaMaseg_headerSrvLocator;
import com.equivida.www.services.clienteequivida.maseg_header.PortType;
import com.equivida.www.services.clienteequivida.maseg_header.Request;
import com.equivida.www.services.clienteequivida.maseg_header.Response;

public class MainMasegHeader {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		ClienteequividaMaseg_headerSrvLocator locator = new ClienteequividaMaseg_headerSrvLocator();
		PortType service = locator.getPort();

		int cod_aseg = 1;
		int id_persona = 2;
		int cod_figura_aseg = 3;
		int cod_tipo_aseg = 4;
		int cod_imp_aseg = 5;
		int cod_tipo_agente = 2;
		int cod_agente = 3;
		Date fec_alta = new Date();
		Date fec_baja = new Date();
		int cod_ocupacion = 12;
		Date fec_ult_modif = new Date();

		String cod_usuario = "A001";
		String txt_nom_factura = "A002";
		int cod_moneda = 1;
		BigDecimal imp_sueldo = new BigDecimal(cod_moneda);
		Request req = new Request(cod_aseg, id_persona, cod_figura_aseg,
				cod_tipo_aseg, cod_imp_aseg, cod_tipo_agente, cod_agente,
				fec_alta, fec_baja, cod_ocupacion, fec_ult_modif, cod_usuario,
				txt_nom_factura, cod_moneda, imp_sueldo);
		Response res = service.invoke(req);

		System.out.println("-----------------------");
		System.out.println(res.getMsg_error());

	}
}
