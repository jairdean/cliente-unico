/**
 *ParroquiaDaoEjb.java
 *
 *Tue Jan 26 12:38:27 ECT 2016
 */
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.ParroquiaSdDao;
import com.equivida.smartdata.model.ParroquiaSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "ParroquiaSdDao")
public class ParroquiaSdDaoEjb extends GenericDaoEjb<ParroquiaSd, Short>
		implements ParroquiaSdDao {

	public ParroquiaSdDaoEjb() {
		super(ParroquiaSd.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.dao.ParroquiaSdDao#obtenerSinParroquiaPorCanton
	 * (java.lang.Short)
	 */
	@Override
	public ParroquiaSd obtenerSinParroquiaPorCanton(Short secCanton) {
		StringBuffer sql = new StringBuffer(200);

		sql.append("select p from ParroquiaSd p where ");
		sql.append("p.secCanton.secCanton = :secCanton ");
		sql.append("and (p.parroquia is null or p.parroquia = '') ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("secCanton", secCanton);

		List<ParroquiaSd> resp = query.getResultList();

		if (resp != null && !resp.isEmpty()) {
			return resp.get(0);
		}

		return null;
	}
}