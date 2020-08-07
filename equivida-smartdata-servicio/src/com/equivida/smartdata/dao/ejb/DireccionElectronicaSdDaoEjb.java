/**
 *DireccionDaoEjb.java
 *
 *Wed Aug 05 19:07:12 ECT 2020
 */
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.DireccionElectronicaSdDao;
import com.equivida.smartdata.model.DireccionElectronicaSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "DireccionElectronicaSdDao")
public class DireccionElectronicaSdDaoEjb extends GenericDaoEjb<DireccionElectronicaSd, Integer>
		implements DireccionElectronicaSdDao {

	public DireccionElectronicaSdDaoEjb() {
		super(DireccionElectronicaSd.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.TelefonoSdDao#
	 * obtenerDireccionElectronicaByPersonaSecPersona(Integer secPersona)
	 */
	@Override
	public List<DireccionElectronicaSd> obtenerDireccionElectronicaByPersonaSecPersona(Integer secPersona) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("select d from DireccionElectronicaSd d where ");
		sql.append("d.secPersona.secPersona = :secPersona ");

		Query query = em.createQuery(sql.toString());
		query.setParameter("secPersona", secPersona);

		List<DireccionElectronicaSd> direccionElectronicaSdList = query.getResultList();

		if (direccionElectronicaSdList != null && !direccionElectronicaSdList.isEmpty())
			return direccionElectronicaSdList;

		return null;
	}

}