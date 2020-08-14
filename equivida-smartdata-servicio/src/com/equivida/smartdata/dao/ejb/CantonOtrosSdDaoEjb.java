/**
*ProvinciaDaoEjb.java
*
*Fri Aug 14 12:44:41 ECT 2020
*/
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.CantonOtrosSdDao;
import com.equivida.smartdata.model.CantonOtrosSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "CantonOtrosSdDao")
public class CantonOtrosSdDaoEjb extends GenericDaoEjb<CantonOtrosSd, Short> implements CantonOtrosSdDao {

	public CantonOtrosSdDaoEjb() {
		super(CantonOtrosSd.class);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.CantonOtrosSdDao#obtenerSecCantonOtroByCodCantIess(Integer codCantIess)
	 */
	@Override
	public Short obtenerSecCantonOtroByCodCantIess(String codCantIess) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("select d from CantonOtrosSd d where ");
		sql.append("d.codCantonIess = :codCantIess ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("codCantIess", codCantIess);

		List<CantonOtrosSd> cantonOtrosSdList = query.getResultList();

		if (cantonOtrosSdList != null && !cantonOtrosSdList.isEmpty())
			return cantonOtrosSdList.get(0).getSecCanton();

		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.CantonOtrosSdDao#obtenerSecCantonOtroByCodCantIess(Integer codCantIess)
	 */
	@Override
	public Short obtenerSecCantonOtroByCodCantSri(String codCantSri) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("select d from CantonOtrosSd d where ");
		sql.append("d.codCantonSri = :codCantSri ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("codCantSri", codCantSri);

		List<CantonOtrosSd> cantonOtrosSdList = query.getResultList();

		if (cantonOtrosSdList != null && !cantonOtrosSdList.isEmpty())
			return cantonOtrosSdList.get(0).getSecCanton();

		return null;
	}
}