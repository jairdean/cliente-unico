/**
 * 
 */
package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.dto.RespuestaGeneralDto;

/**
 * @author juan
 *
 */
@Local
public interface ConsultaGeneralCUServicio {

	/**
	 * Consulta general de PErsona Natural.
	 * 
	 * @param noDocumento
	 * @param usuario
	 * @param rucPersonaNaturalEnviado
	 * 
	 * @return
	 */
	RespuestaGeneralDto consultaGenerica(String noDocumento, String usuario, String rucPersonaNaturalEnviado);

	/**
	 * Consulta general de Persona Natural y paso a SISE.
	 * 
	 * @param noDocumento
	 * @param noDocumento
	 * @param tipoDocumentoEnviado
	 *            C,R,P
	 * @return
	 */
	RespuestaGeneralDto consultaGenericaConSise(String noDocumento, String usuario, String tipoDocumentoEnviado);

	/**
	 * Consulta general de Persona Juridica.
	 * 
	 * @param noDocumento
	 * @param usuario
	 * @return
	 */
	RespuestaGeneralDto consultaGenericaPersonaJuridica(String noDocumento, String usuario);
}
