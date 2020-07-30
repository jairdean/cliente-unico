package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoDireccionDao;
import com.equivida.modelo.TipoDireccion;
import com.equivida.servicio.TipoDireccionServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoDireccionServicio")
public class TipoDireccionServicioImpl extends
		GenericServiceImpl<TipoDireccion, Short> implements
		TipoDireccionServicio {

	@EJB
	private TipoDireccionDao tipoDireccionDao;

	public GenericDao<TipoDireccion, Short> getDao() {
		return tipoDireccionDao;
	}
}
