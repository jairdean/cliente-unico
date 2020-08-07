/**
*EmpleoDependienteDaoEjb.java
*
*Fri Aug 07 12:55:38 ECT 2020
*/
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.EmpleoDependienteSdDao;
import com.equivida.smartdata.model.EmpleoDependienteSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "EmpleoDependienteSdDao")
public class EmpleoDependienteSdDaoEjb extends GenericDaoEjb<EmpleoDependienteSd, Integer> implements EmpleoDependienteSdDao {

	public EmpleoDependienteSdDaoEjb() {
		super(EmpleoDependienteSd.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.EmpleoDependienteSdDao#obtenerEmpleoDependienteBySecPersonaNatural(Integer secPersonaNatura, Integer secPersonaJuridical)
	 */
	@Override
	public EmpleoDependienteSd obtenerEmpleoDependienteBySecPersonaNatural(Integer secPersonaNatural, Integer secPersonaJuridica) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("SELECT d from EmpleoDependienteSd d where ");
		sql.append("d.personaNatural.secPersonaNatural = :secPersonaNatural ");
		sql.append("AND d.personaJuridica.secPersonaJuridica = :secPersonaJuridica ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("secPersonaNatural", secPersonaNatural);
		query.setParameter("secPersonaJuridica", secPersonaJuridica);

		List<EmpleoDependienteSd> empleoDependienteList = query.getResultList();

		if (empleoDependienteList != null && !empleoDependienteList.isEmpty())
			return empleoDependienteList.get(0);

		return null;
	}
	
}