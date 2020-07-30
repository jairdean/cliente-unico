package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ParroquiaInecDao;
import com.equivida.modelo.ParroquiaInec;
import com.equivida.servicio.ParroquiaInecServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ParroquiaInecServicio")
public class ParroquiaInecServicioImpl extends
		GenericServiceImpl<ParroquiaInec, Short> implements
		ParroquiaInecServicio {

	@EJB
	private ParroquiaInecDao parroquiaInecDao;

	public GenericDao<ParroquiaInec, Short> getDao() {
		return parroquiaInecDao;
	}
}
