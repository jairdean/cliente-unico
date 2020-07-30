/**
 * 
 */
package com.equivida.buenviaje.ws;

import javax.ejb.Local;

import com.equivida.buenviaje.dto.ConsultaIn;
import com.equivida.buenviaje.dto.ConsultaOut;

/**
 * @author juan
 *
 */
@Local
public interface OrquestaServicio {
	/**
	 * Procesa la peticion del WS de consulta.
	 * 
	 * @param datosConsulta
	 * @return
	 */
	ConsultaOut procesarPeticion(ConsultaIn datosConsulta);
}
