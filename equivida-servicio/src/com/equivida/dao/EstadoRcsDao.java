package com.equivida.dao;

import java.util.List;

import com.equivida.modelo.EstadoRcs;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * Interfaz de acceso a datos de la entidad EstadoRcs
 * 
 * @author Daniel Cardenas
 * 
 */
public interface EstadoRcsDao extends GenericDao<EstadoRcs, Character> {
	
	List<EstadoRcs> obtenerTodos();
}
