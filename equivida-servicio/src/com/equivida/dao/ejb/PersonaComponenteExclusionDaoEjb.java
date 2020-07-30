package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.PersonaComponenteExclusionDao;
import com.equivida.modelo.PersonaComponenteExclusion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "PersonaComponenteExclusionDao")
public class PersonaComponenteExclusionDaoEjb extends
		GenericDaoEjb<PersonaComponenteExclusion, Integer> implements
		PersonaComponenteExclusionDao {

	public PersonaComponenteExclusionDaoEjb() {
		super(PersonaComponenteExclusion.class);
	}

}
