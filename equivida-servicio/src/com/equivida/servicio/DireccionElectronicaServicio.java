package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.DireccionElectronica;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Local
public interface DireccionElectronicaServicio extends GenericService<DireccionElectronica, Integer> {

	List<DireccionElectronica> obtenerPorPersona(Integer secPersona);

	/**
	 * Se persiste DireccionElectronica.
	 * 
	 * @param direccionElectronica
	 */
	void persistir(DireccionElectronica direccionElectronica);
}