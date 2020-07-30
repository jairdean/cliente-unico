package com.equivida.sise.ws.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.sise.rs.RsActualizacionDatos;
import com.equivida.sise.sp.ActualizacionDatosSp;
import com.equivida.sise.util.DateUtil;
import com.equivida.sise.ws.ActualizacionDatosWs;

@Stateless(name = "ActualizacionDatosWs")
@WebService(endpointInterface = "com.equivida.sise.ws.ActualizacionDatosWs")
@Remote(ActualizacionDatosWs.class)
public class ActualizacionDatosWsImpl implements ActualizacionDatosWs {

	@EJB
	private ActualizacionDatosSp actualizacionDatosSp;

	@Override
	@WebMethod
	public @WebResult(name = "ActualizacionDatos")
	RsActualizacionDatos llamarActualizacionDatosSp(BigDecimal i_imp_poliza,
			BigDecimal i_imp_nro_asegurado, BigDecimal i_imp_nro_pariente,
			BigDecimal i_imp_tipo_identificacion, String i_txt_identificacion,
			String i_txt_primer_apellido, String i_txt_segundo_apellido,
			String i_txt_nombres, String i_dat_fecha_nacimiento,
			String i_txt_direccion_principal, BigDecimal i_imp_provincia,
			BigDecimal i_imp_canton, String i_txt_direccion_domicilio,
			BigDecimal i_imp_provinciad, BigDecimal i_imp_cantond,
			String i_txt_telefono_principal, String i_txt_telefono_celular,
			BigDecimal i_imp_operadora, String i_txt_direccion_mail) {

		System.out.println("en web service ActualizacionDatosWs");

		RsActualizacionDatos respuesta = null;
		try {
			respuesta = actualizacionDatosSp.llamarActualizacionDatosSp(
					i_imp_poliza, i_imp_nro_asegurado, i_imp_nro_pariente,
					i_imp_tipo_identificacion, i_txt_identificacion,
					i_txt_primer_apellido, i_txt_segundo_apellido,
					i_txt_nombres, DateUtil.getFecha(i_dat_fecha_nacimiento),
					i_txt_direccion_principal, i_imp_provincia, i_imp_canton,
					i_txt_direccion_domicilio, i_imp_provinciad, i_imp_cantond,
					i_txt_telefono_principal, i_txt_telefono_celular,
					i_imp_operadora, i_txt_direccion_mail);

		} catch (SQLException e) {
			System.out.println("ERROR:" + e);
		} catch (ParseException e) {
			respuesta = new RsActualizacionDatos();
			respuesta.setMessage("Formato de fecha incorrecto.");
		}

		System.out.println("fin web service ActualizacionDatosWs");

		return respuesta;
	}
}