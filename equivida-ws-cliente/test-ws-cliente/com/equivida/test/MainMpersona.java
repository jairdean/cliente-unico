package com.equivida.test;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import com.equivida.www.services.clienteequivida.mpersona.ClienteequividaMpersonaSrvLocator;
import com.equivida.www.services.clienteequivida.mpersona.PortType;
import com.equivida.www.services.clienteequivida.mpersona.Request;
import com.equivida.www.services.clienteequivida.mpersona.Response;

public class MainMpersona {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		ClienteequividaMpersonaSrvLocator locator = new ClienteequividaMpersonaSrvLocator();
		PortType servicio = locator.getPort();

		String txt_apellido1 = "Cardenas";
		String txt_apellido2 = "Zurita";
		String txt_nombre = "Daniel";
		int cod_tipo_doc = 1; // MPersonaUtil.obtenerTipoIdentificacion('C');
		String nro_doc = "1010101010";
		int cod_tipo_iva = 1;// ???
		String nro_nit = "";// solo cuando es ruc pero que se envia???
		Calendar c1900 = Calendar.getInstance();
		c1900.set(Calendar.YEAR, 1900);
		c1900.set(Calendar.MONTH, 0);
		c1900.set(Calendar.DATE, 1);
		Date fec_nac = c1900.getTime();
		String txt_lugar_nac = "NO DISPONIBLE";// el texto de la parroquia???
		String txt_sexo = "M";// M o F???
		int cod_est_civil = 1;// si es no disponible q se envia?
		String cod_tipo_persona = "F";
		String txt_origen = "A";// NO TOMAR EN CUENTA
		Request request = new Request(txt_apellido1, txt_apellido2, txt_nombre,
				cod_tipo_doc, nro_doc, cod_tipo_iva, nro_nit, fec_nac,
				txt_lugar_nac, txt_sexo, cod_est_civil, cod_tipo_persona,
				txt_origen);

		Response response = servicio.invoke(request);

		System.out.println("***" + response.getMsg_error());
		System.out.println("***" + response.getId_persona());

	}
}
