/**
*ProvinciasOtroservicio.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.ProvinciaOtrosSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface ProvinciaOtrosSdServicio extends GenericService<ProvinciaOtrosSd, Short> {

	/**
	 * Obtener obtenerSecProvinciaOtroByCodProvIess.
	 * 
	 * @param codProvIess
	 * @return secProvinciaOtros
	 */
	ProvinciaOtrosSd obtenerSecProvinciaOtroByCodProvIess(Integer codProvIess);
	
}