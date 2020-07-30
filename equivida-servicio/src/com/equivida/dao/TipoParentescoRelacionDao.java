package com.equivida.dao;

import java.util.List;

import com.equivida.modelo.TipoParentescoRelacion;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Daniel Cardenas
 * 
 */
public interface TipoParentescoRelacionDao extends GenericDao<TipoParentescoRelacion, Short> {

	List<TipoParentescoRelacion> obtenerPorIds(short[] ids);

}
