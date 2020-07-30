package com.equivida.dao;

import com.equivida.modelo.ContactoPreferido;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
public interface ContactoPreferidoDao extends
		GenericDao<ContactoPreferido, Long> {
	
	void updateLight(ContactoPreferido contactoPreferido);

}
