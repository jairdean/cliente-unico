/**
 * 
 */
package com.equivida.databook.client;

import com.equivida.databook.exception.DatabookException;
import com.equivida.databook.model.Registros;

/**
 * @author juan
 *
 */
public interface DatabookService {
	/**
	 * Consula en Databook por numero de cedula.
	 * 
	 * @param cedula
	 * @return
	 */
	Registros consultaDatabook() throws DatabookException;
}
