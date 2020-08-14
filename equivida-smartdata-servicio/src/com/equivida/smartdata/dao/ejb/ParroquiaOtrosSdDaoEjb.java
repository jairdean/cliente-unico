/**
*ProvinciaDaoEjb.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.ParroquiaOtrosSdDao;
import com.equivida.smartdata.model.ParroquiaOtrosSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "ParroquiaOtrosSdDao")
public class ParroquiaOtrosSdDaoEjb extends GenericDaoEjb<ParroquiaOtrosSd, Short> implements ParroquiaOtrosSdDao {

	public ParroquiaOtrosSdDaoEjb() {
		super(ParroquiaOtrosSd.class);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.ParroquiaOtrosSdDao#obtenerSecParroquiaOtroByCodParrIess(String codParrIess)
	 */
	@Override
	public ParroquiaOtrosSd obtenerSecParroquiaOtroByCodParrIess(String codParrIess) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("select d from ParroquiaOtrosSd d where ");
		sql.append("d.codParroquiaIess = :codParrIess ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("codParrIess", codParrIess);

		List<ParroquiaOtrosSd> parroquiaOtrosSdList = query.getResultList();

		if (parroquiaOtrosSdList != null && !parroquiaOtrosSdList.isEmpty())
			return parroquiaOtrosSdList.get(0);

		return null;
	}
}