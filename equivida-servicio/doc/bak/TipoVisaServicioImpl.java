package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoVisaDao;
import com.equivida.modelo.TipoVisa;
import com.equivida.servicio.TipoVisaServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoVisaServicio")
public class TipoVisaServicioImpl extends GenericServiceImpl<TipoVisa, Short>
		implements TipoVisaServicio {

	@EJB
	private TipoVisaDao tipoVisaDao;

	public GenericDao<TipoVisa, Short> getDao() {
		return tipoVisaDao;
	}
}