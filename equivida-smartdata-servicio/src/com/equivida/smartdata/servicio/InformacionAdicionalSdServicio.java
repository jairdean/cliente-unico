/**
*InformacionAdicionalSdServicio.java
*
*Tue Aug 04 11:15:45 ECT 2020
*/
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.saviasoft.persistence.util.service.GenericService;
import com.equivida.smartdata.model.InformacionAdicionalSd;

@Local
public interface InformacionAdicionalSdServicio extends GenericService<InformacionAdicionalSd, Integer> {

	/**
	 * Crea InformacionAdicionalSd.
	 * 
	 * @param InformacionAdicionalSd
	 * @return boolean
	 */
	void crearInformacionAdicional(InformacionAdicionalSd informacionAdicionalSd);

	/**
	 * Obtener InformacionAdicionalSd.
	 * 
	 * @param secPersonaNatural
	 * @return InformacionAdicionalSd
	 */
	InformacionAdicionalSd obtenerInformacionAdicionalBySecPersonaNatural(Integer secPersonaNatural);
}