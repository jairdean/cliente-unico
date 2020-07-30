package com.equivida.dao;

import com.equivida.modelo.DireccionTelefono;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
public interface DireccionTelefonoDao extends
		GenericDao<DireccionTelefono, Integer> {
	
	void updateLigth(DireccionTelefono direccionTelefono);

}
