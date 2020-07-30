package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RespuestaInsertMPersona;

@WebService
@SOAPBinding(style = Style.RPC)
public interface InsertMPersonaWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	RespuestaInsertMPersona ws_sise_insert_mpersona(
			@WebParam(name = "txt_apellido1") String txt_apellido1,
			@WebParam(name = "txt_apellido2") String txt_apellido2,
			@WebParam(name = "txt_nombre") String txt_nombre,
			@WebParam(name = "cod_tipo_doc") BigDecimal cod_tipo_doc,
			@WebParam(name = "nro_doc") String nro_doc,
			@WebParam(name = "cod_tipo_iva") BigDecimal cod_tipo_iva,
			@WebParam(name = "nro_nit") String nro_nit,
			@WebParam(name = "fec_nac") String fec_nac,
			@WebParam(name = "txt_lugar_nac") String txt_lugar_nac,
			@WebParam(name = "txt_sexo") String txt_sexo,
			@WebParam(name = "cod_est_civil") BigDecimal cod_est_civil,
			@WebParam(name = "cod_tipo_persona") String cod_tipo_persona,
			@WebParam(name = "txt_origen") String txt_origen,
			@WebParam(name = "txt_nombres_conyuge") String txt_nombres_conyuge,
			@WebParam(name = "txt_apellidos_conyuge") String txt_apellidos_conyuge,
			@WebParam(name = "cod_tipo_doc_conyuge") BigDecimal cod_tipo_doc_conyuge,
			@WebParam(name = "nro_doc_conyugue") String nro_doc_conyugue,
			@WebParam(name = "campo_in_1") String campo_in_1,
			@WebParam(name = "campo_in_2") String campo_in_2,
			@WebParam(name = "campo_in_3") String campo_in_3,
			@WebParam(name = "campo_in_4") String campo_in_4,
			@WebParam(name = "campo_in_5") String campo_in_5);

}