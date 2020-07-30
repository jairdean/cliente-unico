/**
 * 
 */
package com.equivida.buenviaje.ws;

import javax.ejb.Local;

import com.equivida.buenviaje.dto.ConsultaIn;

/**
 * @author juan
 *
 */
@Local
public interface LlamadoRcsAsyncServicio {
	/**
	 * Llama servicio de RCS.
	 * 
	 * @param datosConsulta
	 */
	void llamarRcs(ConsultaIn datosConsulta);
}
