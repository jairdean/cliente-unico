package com.equivida.sise.ws;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.rs.RsCumulosDePago;

@WebService
@SOAPBinding(style = Style.RPC)
public interface CumulosDePagoWs {

	List<RsCumulosDePago> consultar(
			@WebParam(name = "cedula") String numeroDocumento);

}