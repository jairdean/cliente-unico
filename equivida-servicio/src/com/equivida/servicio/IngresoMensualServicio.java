package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.IngresoMensual;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface IngresoMensualServicio extends GenericService<IngresoMensual, Integer> {
	/**
	 * Se persiste IngresoMensual.
	 * 
	 * @param ingresoMensual
	 */
	void persistir(IngresoMensual ingresoMensual);
}
