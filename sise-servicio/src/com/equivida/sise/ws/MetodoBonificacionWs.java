package com.equivida.sise.ws;

import java.math.BigDecimal;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsMetodoBonificacion;

@WebService
@SOAPBinding(style = Style.RPC)
public interface MetodoBonificacionWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	RsMetodoBonificacion llamarMetodoBonificacionSp(
			@WebParam(name = "i_modo") BigDecimal i_modo,
			@WebParam(name = "i_id_session") BigDecimal i_id_session,
			@WebParam(name = "i_cod_procedimiento") BigDecimal i_cod_procedimiento,
			@WebParam(name = "i_cod_tipo_doc") BigDecimal i_cod_tipo_doc,
			@WebParam(name = "i_nro_doc") String i_nro_doc,
			@WebParam(name = "i_nro_op") BigDecimal i_nro_op,
			@WebParam(name = "i_nro_pieza_dental") BigDecimal i_nro_pieza_dental);

}