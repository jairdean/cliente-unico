/**
*ProvinciasOtroservicio.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.CantonOtrosSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface CantonOtrosSdServicio extends GenericService<CantonOtrosSd, Short> {

	/**
	 * Obtener obtenerSecCantonOtroByCodCantIess.
	 * 
	 * @param codCantIess
	 * @return secCantonOtros
	 */
	Short obtenerSecCantonOtroByCodCantIess(String codCantIess);
	
	/**
	 * Obtener obtenerSecCantonOtroByCodCantSri.
	 * 
	 * @param codCantSri
	 * @return secCantonOtros
	 */
	Short obtenerSecCantonOtroByCodCantSri(String codCantSri);
}