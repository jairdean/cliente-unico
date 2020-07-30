package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaJuridica;
import com.equivida.servicio.PersonaJuridicaServicio;
import com.equivida.servicio.PersonaServicio;
import com.equivida.servicio.SeguroIndividualServicio;
import com.equivida.servicio.SeguroIndividualServicioRemoto;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "SeguroIndividualServicio")
public class SeguroIndividualServicioImpl implements SeguroIndividualServicio,
		SeguroIndividualServicioRemoto {

	private Logger log = Logger.getLogger(SeguroIndividualServicioImpl.class);

	@EJB
	private PersonaServicio personaServicio;

	@EJB
	private PersonaJuridicaServicio personaJuridicaServicio;

	@Override
	public void crearSolicitudPersonaJuridica(PersonaJuridica personaJuridica) {
		log.debug("creando solicitud PJuridica ...");

		Persona persona = personaJuridica.getPersona();

		if (persona == null) {
			throw new RuntimeException("no se ha ingresado persona!");
		}

		// representanteLegal

		// genera denominacion
		persona.setDenominacion(personaJuridica.getRazonSocial());// la razon
																	// social

		log.debug("crea persona juridica");
		// TODO como es esta logica? si ya existe persona, verificar si es
		// cliente o como?
		personaServicio.create(persona);
		log.debug("fin persona");
		//direccionTelefonoServicio.guardarLista(persona
		//		.getDireccionTelefonoCollection());

		personaJuridicaServicio.create(personaJuridica);

	}

}