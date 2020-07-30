package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.InformacionAdicionalDao;
import com.equivida.modelo.InformacionAdicional;
import com.equivida.servicio.InformacionAdicionalServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "InformacionAdicionalServicio")
public class InformacionAdicionalServicioImpl extends
		GenericServiceImpl<InformacionAdicional, Integer> implements
		InformacionAdicionalServicio {

	@EJB
	private InformacionAdicionalDao informacionAdicionalDao;

	public GenericDao<InformacionAdicional, Integer> getDao() {
		return informacionAdicionalDao;
	}
}
