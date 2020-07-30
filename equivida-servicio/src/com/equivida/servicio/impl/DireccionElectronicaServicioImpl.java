package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.DireccionElectronicaDao;
import com.equivida.modelo.DireccionElectronica;
import com.equivida.servicio.DireccionElectronicaServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "DireccionElectronicaServicio")
public class DireccionElectronicaServicioImpl extends GenericServiceImpl<DireccionElectronica, Integer>
		implements DireccionElectronicaServicio {

	@EJB
	private DireccionElectronicaDao direccionElectronicaDao;

	public GenericDao<DireccionElectronica, Integer> getDao() {
		return direccionElectronicaDao;
	}

	@Override
	public List<DireccionElectronica> obtenerPorPersona(Integer secPersona) {

		String[] criteriasAnd = { "persona.secPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersona };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		List<DireccionElectronica> lista = direccionElectronicaDao.findByCriterias(criteria);

		return lista;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.DireccionElectronicaServicio#persistir(com.equivida.
	 * modelo.DireccionElectronica)
	 */
	@Override
	public void persistir(DireccionElectronica direccionElectronica) {
		if (direccionElectronica.getSecDireccionElectronica() == null) {
			create(direccionElectronica);
		} else {
			update(direccionElectronica);
		}
	}
}
