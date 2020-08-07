/**
*EmpleoDependienteServicioImpl.java
*
*Fri Aug 07 12:55:38 ECT 2020
*/
package com.equivida.smartdata.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.smartdata.dao.EmpleoDependienteSdDao;
import com.equivida.smartdata.model.EmpleoDependienteSd;
import com.equivida.smartdata.servicio.EmpleoDependienteSdServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

@Stateless(name = "EmpleoDependienteSdServicio")
public class EmpleoDependienteSdServicioImpl extends GenericServiceImpl<EmpleoDependienteSd, Integer>
		implements EmpleoDependienteSdServicio {

	@EJB
	private EmpleoDependienteSdDao empleoDependienteDao;

	@Override
	public GenericDao<EmpleoDependienteSd, Integer> getDao() {
		return empleoDependienteDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.servicio.EmpleoDependienteServicio#existeRelacion(
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean existeRelacion(Integer secPersonaNatural, Integer secPersonaJuridica) {
		String[] criteriasAnd = { "personaNatural.secPersonaNatural", "personaJuridica.secPersonaJuridica" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersonaNatural, secPersonaJuridica };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);
		List<EmpleoDependienteSd> edList = findByCriterias(criteria);

		boolean resp = false;
		if (edList != null && !edList.isEmpty()) {
			resp = true;
		}

		return resp;
	}

		/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.EmpleoDependienteSdServicio#
	 * crearEmpleoDependiente(com.equivida.smartdata.dto.EmpleoDependienteSdDao)
	 */
	@Override
	public void crearEmpleoDependiente(EmpleoDependienteSd empleoDependienteSd) {
		empleoDependienteDao.save(empleoDependienteSd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.servicio.EmpleoDependienteSdServicio#
	 * obtenerEmpleoDependienteBySecPersonaNatural(Integer secPersonaNatural, Integer secPersonaJuridica)
	 */
	@Override
	public EmpleoDependienteSd obtenerEmpleoDependienteBySecPersonaNatural(Integer secPersonaNatural, Integer secPersonaJuridica) {
		return empleoDependienteDao.obtenerEmpleoDependienteBySecPersonaNatural(secPersonaNatural, secPersonaJuridica);
	}

}
