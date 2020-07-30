package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ProvinciaDao;
import com.equivida.modelo.Provincia;
import com.equivida.servicio.ProvinciaServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ProvinciaServicio")
public class ProvinciaServicioImpl extends GenericServiceImpl<Provincia, Short>
		implements ProvinciaServicio {

	@EJB
	private ProvinciaDao provinciaDao;

	public GenericDao<Provincia, Short> getDao() {
		return provinciaDao;
	}
}
