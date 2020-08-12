/**
*PersonaJuridicaDao.java
*
*Tue Jan 26 12:40:53 ECT 2016
*/
package com.equivida.smartdata.dao;

import javax.ejb.Local;

import com.equivida.smartdata.model.PersonaJuridicaSd;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface PersonaJuridicaSdDao extends GenericDao<PersonaJuridicaSd, Integer> {

	PersonaJuridicaSd obtenerPersonaJuridicaPorIdentifiacion(String identificacion);
}