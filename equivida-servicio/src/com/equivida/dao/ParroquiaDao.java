package com.equivida.dao;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.Parroquia;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface ParroquiaDao extends GenericDao<Parroquia, Short> {
	/**
	 * Consulta parroquias de un canton.
	 * 
	 * @param codigoCanton
	 * @return
	 */
	List<Parroquia> getByCantonNativeQuery(Short codigoCanton);
}
