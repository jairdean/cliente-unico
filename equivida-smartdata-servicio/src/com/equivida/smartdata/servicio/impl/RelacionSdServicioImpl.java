/**
*RelacionservicioImpl.java
*
*Thu Feb 18 10:46:12 ECT 2016
*/
package com.equivida.smartdata.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.RelacionSdDao;
import com.equivida.smartdata.model.RelacionSd;
import com.equivida.smartdata.servicio.RelacionSdServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

@Stateless(name = "RelacionSdServicio")
public class RelacionSdServicioImpl extends GenericServiceImpl<RelacionSd, Integer> implements RelacionSdServicio {

	@EJB
	private RelacionSdDao relacionDao;

	@Override
	public GenericDao<RelacionSd, Integer> getDao() {
		return relacionDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.servicio.RelacionServicio#existeParentesco(java.
	 * lang.Integer, java.lang.Integer, java.lang.Short)
	 */
	@Override
	public boolean existeParentesco(Integer idPersonaP, Integer idPersonaR, Short tipoParentesco) {
		String[] criteriasAnd = { "personaP.secPersona", "personaR.secPersona", "tipoParentesco.codTipoParentesco" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.SHORT_EQUALS };
		Object[] valuesCriteriaAnd = { idPersonaP, idPersonaR, tipoParentesco };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		List<RelacionSd> relacionLista = findByCriterias(criteria);

		boolean resp = false;

		if (relacionLista != null && !relacionLista.isEmpty()) {
			resp = true;
		}

		return resp;
	}

}
