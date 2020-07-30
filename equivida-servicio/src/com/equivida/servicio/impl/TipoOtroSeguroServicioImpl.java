package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoOtroSeguroDao;
import com.equivida.modelo.TipoOtroSeguro;
import com.equivida.servicio.TipoOtroSeguroServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoOtroSeguroServicio")
public class TipoOtroSeguroServicioImpl extends
		GenericServiceImpl<TipoOtroSeguro, Short> implements
		TipoOtroSeguroServicio {

	@EJB
	private TipoOtroSeguroDao tipoOtroSeguroDao;

	public GenericDao<TipoOtroSeguro, Short> getDao() {
		return tipoOtroSeguroDao;
	}
}
