/**
 * 
 */
package com.equivida.buenviaje.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.buenviaje.dto.ConsultaIn;
import com.equivida.buenviaje.dto.PersonaConsulta;
import com.equivida.dto.PersonaDto;
import com.equivida.dto.RcsRespuestaDto;
import com.equivida.ws.RcsWs;

/**
 * @author juan
 *
 */
@Stateless(name = "LlamadoRcsAsyncServicio")
public class LlamadoRcsAsyncServicioImpl implements LlamadoRcsAsyncServicio {
	private static final Logger LOG = Logger
			.getLogger(LlamadoRcsAsyncServicioImpl.class.getName());

	@EJB(mappedName = "RcsWs/local")
	private RcsWs rcsWs;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.buenviaje.ws.LlamadoRcsAsyncServicio#llamarRcs(com.equivida
	 * .buenviaje.dto.ConsultaIn)
	 */
	@Override
	@Asynchronous
	public void llamarRcs(ConsultaIn datosConsulta) {
		LOG.info(">>> Llama a servicio de RCS asincrono");
		List<PersonaDto> personas = new ArrayList<PersonaDto>();

		for (PersonaConsulta pc : datosConsulta
				.getPersonaConsultaListaNoRepetidos()) {
			PersonaDto dto = new PersonaDto();
			dto.setIdentificacion(pc.getNoDocumento());
			dto.setApellidoPaterno(pc.getPrimerApellido());
			dto.setPrimerNombre(pc.getPrimerNombre());
			dto.setTipoIndentificacion(pc.getTipoDocumento().toString());

			personas.add(dto);
		}

		List<RcsRespuestaDto> respuestaLista = rcsWs.buscarRcs(personas,
				"buenviaje");
		if (respuestaLista != null && !respuestaLista.isEmpty()) {
			for (RcsRespuestaDto rcs : respuestaLista) {
				LOG.info("indentificacion: " + rcs.getIndentificacion()
						+ " - error: " + rcs.getError() + " - contenidoXml: "
						+ rcs.getContenidoXml());
			}
		}

	}

}
