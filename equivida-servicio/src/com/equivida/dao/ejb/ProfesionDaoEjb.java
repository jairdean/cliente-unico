package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.ProfesionDao;
import com.equivida.modelo.Profesion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "ProfesionDao")
public class ProfesionDaoEjb extends GenericDaoEjb<Profesion, Short> implements
		ProfesionDao {

	public ProfesionDaoEjb() {
		super(Profesion.class);
	}

}
