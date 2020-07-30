package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.BeneficiarioPolizaDao;
import com.equivida.modelo.BeneficiarioPoliza;
import com.equivida.servicio.BeneficiarioPolizaServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "BeneficiarioPolizaServicio")
public class BeneficiarioPolizaServicioImpl extends
		GenericServiceImpl<BeneficiarioPoliza, Integer> implements
		BeneficiarioPolizaServicio {

	@EJB
	private BeneficiarioPolizaDao beneficiarioPolizaDao;

	public GenericDao<BeneficiarioPoliza, Integer> getDao() {
		return beneficiarioPolizaDao;
	}
}
