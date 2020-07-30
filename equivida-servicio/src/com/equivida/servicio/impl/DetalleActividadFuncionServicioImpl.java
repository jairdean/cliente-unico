package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.DetalleActividadFuncionDao;
import com.equivida.modelo.DetalleActividadFuncion;
import com.equivida.servicio.DetalleActividadFuncionServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "DetalleActividadFuncionServicio")
public class DetalleActividadFuncionServicioImpl extends
		GenericServiceImpl<DetalleActividadFuncion, Integer> implements
		DetalleActividadFuncionServicio {

	@EJB
	private DetalleActividadFuncionDao detalleActividadFuncionDao;

	public GenericDao<DetalleActividadFuncion, Integer> getDao() {
		return detalleActividadFuncionDao;
	}

	@Override
	public DetalleActividadFuncion buscarPorSecPersonaNatural(
			Integer secPersonaNatural) {

		String[] criteriasAnd = { "secPersonaNatural" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersonaNatural };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<DetalleActividadFuncion> lista = detalleActividadFuncionDao
				.findByCriterias(criteria);

		DetalleActividadFuncion d = null;

		int total = lista.size();
		if (total > 0) {
			d = lista.get(0);
			if (total > 1) {
				System.out
						.println("ERROR, existe mas de una DetalleActividadFuncion!!! para secpersonaNatural:"
								+ secPersonaNatural);
			}
		}

		return d;
	}
}
