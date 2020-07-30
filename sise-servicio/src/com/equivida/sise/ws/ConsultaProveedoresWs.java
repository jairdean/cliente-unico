package com.equivida.sise.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import com.equivida.sise.rs.RsConsultaProveedores;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ConsultaProveedoresWs {

	// @WebMethod
	// @WebResult(name = "RespuestaInsertMPersona")
	List<RsConsultaProveedores> llamarConsultaProveedoresSp(
			@WebParam(name = "nroDoc") String nroDoc,
			@WebParam(name = "snActivo") Integer snActivo);

}