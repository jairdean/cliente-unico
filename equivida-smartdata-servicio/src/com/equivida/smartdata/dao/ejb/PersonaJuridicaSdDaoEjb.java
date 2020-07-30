/**
*PersonaJuridicaDaoEjb.java
*
*Tue Jan 26 12:40:53 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.PersonaJuridicaSdDao;
import com.equivida.smartdata.model.PersonaJuridicaSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "PersonaJuridicaSdDao")
public class PersonaJuridicaSdDaoEjb extends GenericDaoEjb<PersonaJuridicaSd, Integer> implements PersonaJuridicaSdDao {

	public PersonaJuridicaSdDaoEjb() {
		super(PersonaJuridicaSd.class);
	}
}