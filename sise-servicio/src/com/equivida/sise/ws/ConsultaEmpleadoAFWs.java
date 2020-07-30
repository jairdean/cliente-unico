package com.equivida.sise.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsConsultaEmpleadoAF;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ConsultaEmpleadoAFWs {

	List<RsConsultaEmpleadoAF> llamarConsultaEmpleadoAFSp(
			@WebParam(name = "cedula") String cedula);

}