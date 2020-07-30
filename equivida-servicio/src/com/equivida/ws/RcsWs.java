package com.equivida.ws;

import java.util.List;

import javax.ejb.Local;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.equivida.dto.PersonaDto;
import com.equivida.dto.RcsRespuestaDto;

@Local
@WebService
@SOAPBinding(style = Style.RPC)
public interface RcsWs {

	List<RcsRespuestaDto> buscarRcs(List<PersonaDto> personas, String aplicacion);

}
