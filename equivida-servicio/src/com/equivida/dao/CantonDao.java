package com.equivida.dao;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.Canton;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface CantonDao extends GenericDao<Canton, Short> {
	/**
	 * Obtiene provincias de un Pais.
	 * 
	 * @return
	 */
	List<Canton> getByProvinciaNativeQuery(Short codigoProvincia);
}
