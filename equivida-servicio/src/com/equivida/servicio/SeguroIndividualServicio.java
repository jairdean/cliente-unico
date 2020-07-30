package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.PersonaJuridica;

/**
 * @author Daniel Cardenas
 */
@Local
public interface SeguroIndividualServicio {

	void crearSolicitudPersonaJuridica(PersonaJuridica personaJuridica);

}
