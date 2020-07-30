package com.equivida.servicio;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.EstadoRcs;
import com.saviasoft.persistence.util.service.GenericService;

/**
 * @author Daniel Cardenas
 * 
 */
@Local
public interface EstadoRcsServicio extends GenericService<EstadoRcs, Character> {
	
	List<EstadoRcs> obtenerTodos();
}
