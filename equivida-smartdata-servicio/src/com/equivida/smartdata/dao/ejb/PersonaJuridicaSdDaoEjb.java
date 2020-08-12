/**
*PersonaJuridicaDaoEjb.java
*
*Tue Jan 26 12:40:53 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.PersonaJuridicaSdDao;
import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.equivida.smartdata.model.PersonaJuridicaSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "PersonaJuridicaSdDao")
public class PersonaJuridicaSdDaoEjb extends GenericDaoEjb<PersonaJuridicaSd, Integer> implements PersonaJuridicaSdDao {

	public PersonaJuridicaSdDaoEjb() {
		super(PersonaJuridicaSd.class);
	}
	
	
	@Override
	public PersonaJuridicaSd obtenerPersonaJuridicaPorIdentifiacion(String identificacion) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("select d from PersonaJuridicaSd d where ");
		sql.append("d.identificacion = :identificacion ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("identificacion", identificacion);

		List<PersonaJuridicaSd> personaJuridicaList = query.getResultList();

		if (personaJuridicaList != null && !personaJuridicaList.isEmpty())
			return personaJuridicaList.get(0);

		return null;
	}
}