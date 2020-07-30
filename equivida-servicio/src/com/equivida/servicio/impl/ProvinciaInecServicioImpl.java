package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ProvinciaInecDao;
import com.equivida.modelo.ProvinciaInec;
import com.equivida.servicio.ProvinciaInecServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ProvinciaInecServicio")
public class ProvinciaInecServicioImpl extends
		GenericServiceImpl<ProvinciaInec, Short> implements
		ProvinciaInecServicio {

	@EJB
	private ProvinciaInecDao provinciaInecDao;

	public GenericDao<ProvinciaInec, Short> getDao() {
		return provinciaInecDao;
	}
}
