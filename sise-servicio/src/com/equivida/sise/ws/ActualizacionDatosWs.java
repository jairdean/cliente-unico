package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsActualizacionDatos;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ActualizacionDatosWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	RsActualizacionDatos llamarActualizacionDatosSp(
			@WebParam(name = "i_imp_poliza") BigDecimal i_imp_poliza,
			@WebParam(name = "i_imp_nro_asegurado") BigDecimal i_imp_nro_asegurado,
			@WebParam(name = "i_imp_nro_pariente") BigDecimal i_imp_nro_pariente,
			@WebParam(name = "i_imp_tipo_identificacion") BigDecimal i_imp_tipo_identificacion,
			@WebParam(name = "i_txt_identificacion") String i_txt_identificacion,
			@WebParam(name = "i_txt_primer_apellido") String i_txt_primer_apellido,
			@WebParam(name = "i_txt_segundo_apellido") String i_txt_segundo_apellido,
			@WebParam(name = "i_txt_nombres") String i_txt_nombres,
			@WebParam(name = "i_dat_fecha_nacimiento") String i_dat_fecha_nacimiento,
			@WebParam(name = "i_txt_direccion_principal") String i_txt_direccion_principal,
			@WebParam(name = "i_imp_provincia") BigDecimal i_imp_provincia,
			@WebParam(name = "i_imp_canton") BigDecimal i_imp_canton,
			@WebParam(name = "i_txt_direccion_domicilio") String i_txt_direccion_domicilio,
			@WebParam(name = "i_imp_provinciad") BigDecimal i_imp_provinciad,
			@WebParam(name = "i_imp_cantond") BigDecimal i_imp_cantond,
			@WebParam(name = "i_txt_telefono_principal") String i_txt_telefono_principal,
			@WebParam(name = "i_txt_telefono_celular") String i_txt_idei_txt_telefono_celularntificacion,
			@WebParam(name = "i_imp_operadora") BigDecimal i_imp_operadora,
			@WebParam(name = "i_txt_direccion_mail") String i_txt_direccion_mail);

}