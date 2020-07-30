package com.equivida.dao;

import java.util.Collection;

import com.equivida.modelo.TipoHabitoEnfermedad;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * Interfaz que define el comportamiento de las acciones de acceso a base de
 * datos (dao) sobre la entidad TipoHabitoEnfermedad
 * 
 * 
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
public interface TipoHabitoEnfermedadDao extends GenericDao<TipoHabitoEnfermedad, Short> {

	Collection<TipoHabitoEnfermedad> buscarPorCategoria(String[] categorias);

}
