package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.SeguroAdicionalDao;
import com.equivida.modelo.SeguroAdicional;
import com.equivida.servicio.SeguroAdicionalServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "SeguroAdicionalServicio")
public class SeguroAdicionalServicioImpl extends
		GenericServiceImpl<SeguroAdicional, Integer> implements
		SeguroAdicionalServicio {

	@EJB
	private SeguroAdicionalDao seguroAdicionalDao;

	public GenericDao<SeguroAdicional, Integer> getDao() {
		return seguroAdicionalDao;
	}
}
