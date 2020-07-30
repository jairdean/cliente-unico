package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.IngresoAnualDao;
import com.equivida.modelo.IngresoAnual;
import com.equivida.servicio.IngresoAnualServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "IngresoAnualServicio")
public class IngresoAnualServicioImpl extends
		GenericServiceImpl<IngresoAnual, Integer> implements
		IngresoAnualServicio {

	@EJB
	private IngresoAnualDao ingresoAnualDao;

	public GenericDao<IngresoAnual, Integer> getDao() {
		return ingresoAnualDao;
	}
}
