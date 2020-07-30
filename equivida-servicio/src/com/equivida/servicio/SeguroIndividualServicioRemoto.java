package com.equivida.servicio;

import javax.ejb.Remote;

import com.equivida.modelo.PersonaJuridica;

/**
 * @author Daniel Cardenas
 */
@Remote
public interface SeguroIndividualServicioRemoto {

	void crearSolicitudPersonaJuridica(PersonaJuridica personaJuridica);

}
