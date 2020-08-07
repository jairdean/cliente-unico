/**
*EmpleoDependienteDaoEjb.java
*
*Wed Feb 17 12:55:38 ECT 2016
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
	 * @see com.equivida.smartdata.dao.EmpleoDependienteSdDao#obtenerEmpleoDependienteBySecPersonaNatural(Integer secPersonaNatural)
	 */
	@Override
	public EmpleoDependienteSd obtenerEmpleoDependienteBySecPersonaNatural(Integer secPersonaNatural) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("select d from EmpleoDependienteSd d where ");
		sql.append("d.secPersonaNatural.secPersonaNatural = :secPersonaNatural ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("secPersonaNatural", secPersonaNatural);

		List<EmpleoDependienteSd> empleoDependienteList = query.getResultList();

		if (empleoDependienteList != null && !empleoDependienteList.isEmpty())
			return empleoDependienteList.get(0);

		return null;
	}
	
}