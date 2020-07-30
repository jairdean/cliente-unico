package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.InsertDireccionContratante;

@WebService
@SOAPBinding(style = Style.RPC)
public interface InsertDireccionContratanteWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	InsertDireccionContratante spi_mpersona_dir_wkf(@WebParam(name = "id_persona_wkf") BigDecimal id_persona_wkf,
			@WebParam(name = "cod_tipo_dir") BigDecimal cod_tipo_dir, @WebParam(name = "txt_direccion") String txt_direccion,
			@WebParam(name = "cod_municipio") BigDecimal cod_municipio, @WebParam(name = "cod_dpto") BigDecimal cod_dpto,
			@WebParam(name = "cod_pais") BigDecimal cod_pais);

}