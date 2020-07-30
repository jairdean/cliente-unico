package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ParroquiaDao;
import com.equivida.modelo.Parroquia;
import com.equivida.servicio.ParroquiaServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ParroquiaServicio")
public class ParroquiaServicioImpl extends GenericServiceImpl<Parroquia, Short>
		implements ParroquiaServicio {

	@EJB
	private ParroquiaDao parroquiaDao;

	public GenericDao<Parroquia, Short> getDao() {
		return parroquiaDao;
	}
}
