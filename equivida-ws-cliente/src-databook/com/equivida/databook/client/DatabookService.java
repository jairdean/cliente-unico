/**
 * 
 */
package com.equivida.databook.client;

import com.equivida.databook.exception.DatabookException;
import com.equivida.databook.model.Registros;
import com.equivida.databook.model.RegistrosEntity;

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
	
	/**
	 * Consula en Databook por numero de cedula.
	 * 
	 * @param cedula
	 * @return RegistrosEntity
	 */
	RegistrosEntity consultaDatabookRegistrosEntity() throws DatabookException;
}
