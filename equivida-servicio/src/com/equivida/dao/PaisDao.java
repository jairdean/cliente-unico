package com.equivida.dao;

import java.util.List;

import javax.ejb.Local;

import com.equivida.modelo.Pais;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
@Local
public interface PaisDao extends GenericDao<Pais, Short> {
	/**
	 * Busca todos los paises de la tabla.
	 * 
	 * @return
	 */
	List<Pais> getAllNativeQuery();
}
