package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.PersonaPeDao;
import com.equivida.modelo.PersonaPe;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "PersonaPeDao")
public class PersonaPeDaoEjb extends GenericDaoEjb<PersonaPe, Integer>
		implements PersonaPeDao {

	public PersonaPeDaoEjb() {
		super(PersonaPe.class);
	}

}
