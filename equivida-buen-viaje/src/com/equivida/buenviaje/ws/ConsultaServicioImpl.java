/**
 * 
 */
package com.equivida.buenviaje.ws;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.equivida.buenviaje.dto.ConsultaIn;
import com.equivida.buenviaje.dto.ConsultaOut;

/**
 * @author juan
 *
 */
@WebService
@Stateless(name = "ConsultaServicio")
public class ConsultaServicioImpl implements ConsultaServicio {

	private static final Logger LOG = Logger
			.getLogger(LlamadoRcsAsyncServicioImpl.class.getName());
	
	@EJB
	private OrquestaServicio orquestaServicio;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.buenviaje.ws.ConsultaServicio#consultar(com.equivida.buenviaje
	 * .dto.ConsultaIn)
	 */
	@WebMethod
	@Override
	public @WebResult(name = "respuestas") ConsultaOut consultar(
			@WebParam(name = "personasConsulta") ConsultaIn datosConsulta) {
		LOG.info("EJECUTANDO WS CONSULTASERVICIOIMPL ....");
		return orquestaServicio.procesarPeticion(datosConsulta);
	}
}
