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
public interface ConsultaServicio {
	/**
	 * Permite consultar los datos de varias personas a la vez.
	 * 
	 * @param datosConsulta
	 * @return
	 */
	ConsultaOut consultar(ConsultaIn datosConsulta);
}
