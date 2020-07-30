package com.equivida.dao;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.EstadoCivil;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface EstadoCivilDao extends GenericDao<EstadoCivil, Short> {
	/**
	 * Obtiene todos los estados civiles.
	 * 
	 * @return
	 */
	List<EstadoCivil> getAllNativeQuery();
}
