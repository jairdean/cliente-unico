package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.PersonaPeDao;
import com.equivida.modelo.PersonaPe;
import com.equivida.servicio.PersonaPeServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "PersonaPeServicio")
public class PersonaPeServicioImpl extends
		GenericServiceImpl<PersonaPe, Integer> implements PersonaPeServicio {

	@EJB
	private PersonaPeDao personaPeDao;

	public GenericDao<PersonaPe, Integer> getDao() {
		return personaPeDao;
	}
}
