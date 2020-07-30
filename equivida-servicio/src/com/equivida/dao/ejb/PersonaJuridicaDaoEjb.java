package com.equivida.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.PersonaJuridicaDao;
import com.equivida.modelo.PersonaJuridica;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "PersonaJuridicaJuridicaDao")
public class PersonaJuridicaDaoEjb extends GenericDaoEjb<PersonaJuridica, Integer> implements PersonaJuridicaDao {

	public PersonaJuridicaDaoEjb() {
		super(PersonaJuridica.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.dao.PersonaJuridicaDao#obtenerPorNoDocumento(java.lang.String )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PersonaJuridica obtenerPorNoDocumento(String noDocumento) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("select p from PersonaJuridica p where p.identificacion = :noDocumento");

		Query query = em.createQuery(sql.toString());
		query.setParameter("noDocumento", noDocumento);

		List<PersonaJuridica> respList = query.getResultList();

		if (respList != null && !respList.isEmpty()) {
			return respList.get(0);
		}

		return null;
	}

}
