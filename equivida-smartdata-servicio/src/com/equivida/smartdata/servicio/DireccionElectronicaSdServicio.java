/**
 *Direccionservicio.java
 *
 *Tue Jan 26 12:34:07 ECT 2016
 */
package com.equivida.smartdata.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.smartdata.model.DireccionElectronicaSd;
import com.saviasoft.persistence.util.service.GenericService;

@Local
public interface DireccionElectronicaSdServicio extends GenericService<DireccionElectronicaSd, Integer> {
	/**
	 * Ingresa una Direccion ElectronicaSd.
	 * 
	 * @param DireccionElectronicaSd
	 * @return
	 */
	void ingresarDireccionElectronica(DireccionElectronicaSd direccion);
	
	/**
	 * Busca Lista DireccionElectronicaSd por secPersona
	 * 
	 * @param secPersona
	 * @return List<DireccionElectronicaSd>
	 */
	List<DireccionElectronicaSd> obtenerDireccionElectronicaByPersonaSecPersona(Integer secPersona);
}