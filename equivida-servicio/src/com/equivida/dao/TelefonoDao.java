package com.equivida.dao;

import com.equivida.modelo.Telefono;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
public interface TelefonoDao extends GenericDao<Telefono, Integer> {

	void updateLight(Telefono telefono);
	
}
