package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.IngresoMensualAdicional;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface IngresoMensualAdicionalServicio extends GenericService<IngresoMensualAdicional, Integer> {
	/**
	 * Se persiste IngresoMensualAdicional.
	 * 
	 * @param ingresoMensualAdicional
	 */
	void persistir(IngresoMensualAdicional ingresoMensualAdicional);
}
