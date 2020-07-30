package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsRegistroClinicas;
import com.equivida.sise.sp.RegistroClinicasSp;
import com.equivida.sise.ws.RegistroClinicasWs;

@Stateless(name = "RegistroClinicasWs")
@WebService(endpointInterface = "com.equivida.sise.ws.RegistroClinicasWs")
@Remote(RegistroClinicasWs.class)
public class RegistroClinicasWsImpl implements RegistroClinicasWs {

	@EJB
	private RegistroClinicasSp registroClinicasSp;

	@Override
	@WebMethod
	public @WebResult(name = "ConsultaPersona")
	RsRegistroClinicas llamarRegistroClinicasSp(BigDecimal i_cod_tipo_doc,
			String i_nro_doc, String i_txt_apellido1,
			BigDecimal i_cod_especialidad, String i_txt_direccion_principal,
			BigDecimal i_canton, BigDecimal i_provincia, BigDecimal i_ciudad,
			BigDecimal i_sector, String i_txt_direccion_mail,
			String i_txt_telefono, String i_tele_cel,
			BigDecimal i_cod_operadora, String i_contacto) {

		RsRegistroClinicas respuesta = null;
		try {
			respuesta = registroClinicasSp.llamarRegistroClinicasSp(
					i_cod_tipo_doc, i_nro_doc, i_txt_apellido1,
					i_cod_especialidad, i_txt_direccion_principal, i_canton,
					i_provincia, i_ciudad, i_sector, i_txt_direccion_mail,
					i_txt_telefono, i_tele_cel, i_cod_operadora, i_contacto);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		}

		return respuesta;
	}
}