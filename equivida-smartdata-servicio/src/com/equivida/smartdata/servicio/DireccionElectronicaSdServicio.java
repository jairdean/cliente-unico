/**
 *Direccionservicio.java
 *
 *Tue Jan 26 12:34:07 ECT 2016
 */
package com.equivida.smartdata.servicio;

import javax.ejb.Local;

import com.equivida.smartdata.model.DireccionElectronicaSd;
import com.equivida.smartdata.model.DireccionSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface DireccionElectronicaSdServicio extends GenericService<DireccionSd, Integer> {
	/**
	 * Ingresa una direccion.
	 * 
	 * @param direccion
	 * @return
	 */
	void ingresarDireccionElectronica(DireccionElectronicaSd direccion);
}