package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoServicioFinancieroDao;
import com.equivida.modelo.TipoServicioFinanciero;
import com.equivida.servicio.TipoServicioFinancieroServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoServicioFinancieroServicio")
public class TipoServicioFinancieroServicioImpl extends
		GenericServiceImpl<TipoServicioFinanciero, Short> implements
		TipoServicioFinancieroServicio {

	@EJB
	private TipoServicioFinancieroDao tipoServicioFinancieroDao;

	public GenericDao<TipoServicioFinanciero, Short> getDao() {
		return tipoServicioFinancieroDao;
	}
}
