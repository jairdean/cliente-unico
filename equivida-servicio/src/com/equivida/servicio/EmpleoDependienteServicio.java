package com.equivida.servicio;

import javax.ejb.Local;

import com.equivida.modelo.EmpleoDependiente;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface EmpleoDependienteServicio extends GenericService<EmpleoDependiente, Integer> {
	/**
	 * Se persiste EmpleoDependiente.
	 * 
	 * @param empleoDependiente
	 */
	void persistir(EmpleoDependiente empleoDependiente);
}
