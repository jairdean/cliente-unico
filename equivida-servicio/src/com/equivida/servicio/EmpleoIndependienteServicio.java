package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.EmpleoIndependiente;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface EmpleoIndependienteServicio extends GenericService<EmpleoIndependiente, Integer> {
	/**
	 * Se persiste EmpleoIndependiente.
	 * 
	 * @param empleoIndependiente
	 */
	void persistir(EmpleoIndependiente empleoIndependiente);
}
