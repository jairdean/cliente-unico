package com.equivida.dao;

import java.util.List;

import com.equivida.modelo.Provincia;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
public interface ProvinciaDao extends GenericDao<Provincia, Short> {
	/**
	 * Obtiene provincias de un Pais.
	 * 
	 * @return
	 */
	List<Provincia> getByPaisNativeQuery(Short codigoPais);
}
