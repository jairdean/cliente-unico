package com.equivida.sise.ws;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.sise.dto.ContratanteDto;
import com.equivida.sise.rs.RsContratante;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ContratantesWs {

	// @WebMethod
	// @WebResult(name = "RsContratante")
	RsContratante ws_sise_sp_contratante(
			@WebParam(name = "contratanteData", partName = "contratanteData") ContratanteDto contratanteDto);
	
	
}