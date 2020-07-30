package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.BeneficiarioDao;
import com.equivida.modelo.Beneficiario;
import com.equivida.servicio.BeneficiarioServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "BeneficiarioServicio")
public class BeneficiarioServicioImpl extends GenericServiceImpl<Beneficiario, Integer>
		implements BeneficiarioServicio {

	@EJB
	private BeneficiarioDao beneficiarioDao;

	public GenericDao<Beneficiario, Integer> getDao() {
		return beneficiarioDao;
	}

	/*
	 * 
	 */
	@Override
	public Beneficiario buscarPorNoDocumento(String noDocumento) {
		String[] criteriasAnd = { "identificacion" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS };
		Object[] params = new Object[] { noDocumento };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		List<Beneficiario> beneficiarioList = findByCriterias(criteria);
		Beneficiario beneficiario = null;

		if (beneficiarioList != null && !beneficiarioList.isEmpty()) {
			beneficiario = beneficiarioList.get(0);
		}

		return beneficiario;
	}

}
