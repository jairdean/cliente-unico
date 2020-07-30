package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.HijoDao;
import com.equivida.modelo.Hijo;
import com.equivida.modelo.Persona;
import com.equivida.modelo.PersonaNatural;
import com.equivida.servicio.HijoServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Duval
 */
@Stateless(name = "HijoServicio")
public class HijoServicioImpl extends GenericServiceImpl<Hijo, Integer> implements HijoServicio {

	@EJB
	private HijoDao hijoDao;

	public GenericDao<Hijo, Integer> getDao() {
		return hijoDao;
	}

	@Override
	public List<Hijo> obtenerPorPadre(Integer idPadre) {

		String[] criteriasAnd = { "personaNatural.secPersonaNatural" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] params = new Object[] { idPadre };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, params);

		return findByCriterias(criteria);
	}

	@Override
	public void guardarhijo(Hijo hijo) {

		if (hijo.getSecHijo() == null) {
			hijoDao.save(hijo);
		} else {
			hijoDao.update(hijo);
		}

	}

	@Override
	public void eliminar(Hijo hijo) {

		hijoDao.delete(hijo);

	}

	@Override
	public void guardarActualizarEliminarHijos(Persona persona, PersonaNatural personaNatural) {

		if (persona.getHijos() != null) {
			for (Hijo h : persona.getHijos()) {

				h.setPersonaNatural(personaNatural);

				guardarhijo(h);

			}
		}

		if (persona.getHijosEliminar() != null) {

			for (Hijo h : persona.getHijosEliminar()) {

				eliminar(h);

			}

		}

	}
}
