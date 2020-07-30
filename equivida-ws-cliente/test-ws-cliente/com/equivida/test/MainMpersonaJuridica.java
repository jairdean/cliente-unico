package com.equivida.test;

import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import com.equivida.www.services.clienteequivida.mpersona_juridica.ClienteequividaMpersona_juridicaSrvLocator;
import com.equivida.www.services.clienteequivida.mpersona_juridica.PortType;
import com.equivida.www.services.clienteequivida.mpersona_juridica.Request;
import com.equivida.www.services.clienteequivida.mpersona_juridica.Response;

public class MainMpersonaJuridica {

	public static void main(String[] args) throws ServiceException,
			RemoteException {

		ClienteequividaMpersona_juridicaSrvLocator locator = new ClienteequividaMpersona_juridicaSrvLocator();

		PortType servicio = locator.getPort();

		int id_persona = 1;
		int cod_tipo_soc = 1;
		int cod_sector_mercado = 1;
		int cod_actividad = 1;
		String txt_nombre_contacto = "nombre contacto";
		Date fec_contitucion = new Date();
		String txt_cuidad_const = "quito";
		int cnt_duracion_empresa = 2;
		int imp_capital_social = 1000;
		String txt_sector = "Colon";
		String txt_urbanizacion = "urbanizacion";
		String txt_edificio = "edificio";
		int cod_tipo_empresa = 1;
		String txt_nombre_gerente = "gerente";
		int cnt_tiempo_mercado = 1;
		int imp_prom_ing_mensual = 1000;
		String txt_nombres_rep_legal = "nombres";
		String nro_doc_rep_legal = "nro doc";
		String txt_apellidos_rep_legal = "apellidos";
		int cod_tipo_doc_rep_legal = 1;
		String sn_pep = "pep";
		String sn_relacion_laboral_pep = "relacion";
		String txt_nombre_institucion_pep = "nombre ins";
		int cod_ingreso_pm = 1;
		Request req = new Request(id_persona, cod_tipo_soc, cod_sector_mercado,
				cod_actividad, txt_nombre_contacto, fec_contitucion,
				txt_cuidad_const, cnt_duracion_empresa, imp_capital_social,
				txt_sector, txt_urbanizacion, txt_edificio, cod_tipo_empresa,
				txt_nombre_gerente, cnt_tiempo_mercado, imp_prom_ing_mensual,
				txt_nombres_rep_legal, txt_apellidos_rep_legal,
				cod_tipo_doc_rep_legal, nro_doc_rep_legal, sn_pep,
				sn_relacion_laboral_pep, txt_nombre_institucion_pep,
				cod_ingreso_pm);

		Response response = servicio.invoke(req);

		System.out.println(response.getMsg_error());

	}
}
