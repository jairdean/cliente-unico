/**
*ProvinciasOtroservicio.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;
import com.equivida.smartdata.model.ParroquiaOtrosSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface ParroquiaOtrosSdServicio extends GenericService<ParroquiaOtrosSd, Short> {

	/**
	 * Obtener obtenerSecParroquiaOtroByCodParrIess.
	 * 
	 * @param codParrIess
	 * @return secParroquiaOtros
	 */
	ParroquiaOtrosSd obtenerSecParroquiaOtroByCodParrIess(String codParrIess);

}