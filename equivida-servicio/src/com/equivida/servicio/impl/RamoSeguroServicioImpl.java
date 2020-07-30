package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.RamoSeguroDao;
import com.equivida.modelo.RamoSeguro;
import com.equivida.servicio.RamoSeguroServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "RamoSeguroServicio")
public class RamoSeguroServicioImpl extends
		GenericServiceImpl<RamoSeguro, Short> implements RamoSeguroServicio {

	@EJB
	private RamoSeguroDao ramoSeguroDao;

	public GenericDao<RamoSeguro, Short> getDao() {
		return ramoSeguroDao;
	}

	@Override
	public List<RamoSeguro> obtenerOrdenadoPorNombre() {
		String[] criteriasAnd = null;
		CriteriaTypeEnum[] typesAnd = null;
		Object[] valuesCriteriaAnd = null;

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd, new String[] { "tipoRamo" },
				new boolean[] { true });

		List<RamoSeguro> lista = ramoSeguroDao.findByCriterias(criteria);

		return lista;
	}
}
