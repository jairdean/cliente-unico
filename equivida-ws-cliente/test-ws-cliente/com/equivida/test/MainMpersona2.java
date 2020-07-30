package com.equivida.test;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.equivida.sise.ws.client.InsertMPersonaWs;
import com.equivida.sise.ws.client.RespuestaInsertMPersona;
import com.equivida.sise.ws.client.impl.InsertMPersonaWsImplServiceLocator;

public class MainMpersona2 {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		InsertMPersonaWsImplServiceLocator locator = new InsertMPersonaWsImplServiceLocator();

		String address = "http://localhost:8080/sise-servicio/InsertMPersonaWsImpl";
		URL url;
		try {
			url = new URL(address);

			InsertMPersonaWs servicio = locator
					.getInsertMPersonaWsImplPort(url);

			String txt_apellido1 = "Cardenas";
			String txt_apellido2 = "Zurita";
			String txt_nombre = "Daniel";
			int cod_tipo_doc = 1;// MPersonaUtil.obtenerTipoIdentificacion('C');
			String nro_doc = "1010101010";
			int cod_tipo_iva = 1;// ???
			String nro_nit = "";// solo cuando es ruc pero que se envia???
			// Calendar c1900 = Calendar.getInstance();
			// c1900.set(Calendar.YEAR, 1900);
			// c1900.set(Calendar.MONTH, 0);
			// c1900.set(Calendar.DATE, 1);
			String fec_nac = "2012/01/01";
			String txt_lugar_nac = "NO DISPONIBLE";// el texto de la
													// parroquia???
			String txt_sexo = "M";// M o F???
			int cod_est_civil = 1;// si es no disponible q se envia?
			String cod_tipo_persona = "F";
			String txt_origen = "A";// NO TOMAR EN CUENTA
			String txt_nombres_conyuge = "Nombres Conyuge";
			String txt_apellidos_conyuge = "Apellidos Conyuge";
			int cod_tipo_doc_conyuge = 1;// MPersonaUtil.obtenerTipoIdentificacion('C');
			String nro_doc_conyugue = "1713470126";
			String campo_in_1 = null;
			String campo_in_2 = null;
			String campo_in_3 = null;
			String campo_in_4 = null;
			String campo_in_5 = null;

			// Request request = new Request(txt_apellido1, txt_apellido2,
			// txt_nombre,
			// / cod_tipo_doc, nro_doc, cod_tipo_iva, nro_nit, fec_nac,
			// txt_lugar_nac, txt_sexo, cod_est_civil, cod_tipo_persona,
			// txt_origen);

			RespuestaInsertMPersona response = servicio
					.ws_sise_insert_mpersona(txt_apellido1, txt_apellido2,
							txt_nombre, BigDecimal.valueOf(cod_tipo_doc),
							nro_doc, BigDecimal.valueOf(cod_tipo_iva), nro_nit,
							fec_nac, txt_lugar_nac, txt_sexo,
							BigDecimal.valueOf(cod_est_civil),
							cod_tipo_persona, txt_origen, txt_nombres_conyuge,
							txt_apellidos_conyuge,
							BigDecimal.valueOf(cod_tipo_doc_conyuge),
							nro_doc_conyugue, campo_in_1, campo_in_2,
							campo_in_3, campo_in_4, campo_in_5);

			// Response response = servicio.invoke(request);

			System.out.println("Id persona:" + response.getIdPersona());
			System.out.println("Error:" + response.getTxtError());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
