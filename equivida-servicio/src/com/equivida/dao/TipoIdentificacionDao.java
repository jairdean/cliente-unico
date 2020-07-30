package com.equivida.dao;

import java.util.List;

import com.equivida.modelo.TipoIdentificacion;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
public interface TipoIdentificacionDao extends
		GenericDao<TipoIdentificacion, Character> {
	/**
	 * Obtiene todos los tipos de identificacion.
	 * 
	 * @return
	 */
	List<TipoIdentificacion> getAllNativeQuery();
}
