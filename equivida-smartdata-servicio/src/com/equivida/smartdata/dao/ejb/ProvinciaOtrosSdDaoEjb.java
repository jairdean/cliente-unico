/**
*ProvinciaDaoEjb.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.ProvinciaOtrosSdDao;
import com.equivida.smartdata.model.ProvinciaOtrosSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "ProvinciaOtrosSdDao")
public class ProvinciaOtrosSdDaoEjb extends GenericDaoEjb<ProvinciaOtrosSd, Short> implements ProvinciaOtrosSdDao {

	public ProvinciaOtrosSdDaoEjb() {
		super(ProvinciaOtrosSd.class);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.ProvinciaOtrosSdDao#obtenerSecProvinciaOtroByCodProvIess(Integer codProvIess)
	 */
	@Override
	public Short obtenerSecProvinciaOtroByCodProvIess(Integer codProvIess) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("select d from ProvinciaOtrosSd d where ");
		sql.append("d.codProvinciaIess = :codProvIess ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("codProvIess", codProvIess);

		List<ProvinciaOtrosSd> provinciaOtrosSdList = query.getResultList();

		if (provinciaOtrosSdList != null && !provinciaOtrosSdList.isEmpty())
			return provinciaOtrosSdList.get(0).getSecProvincia();

		return null;
	}
}