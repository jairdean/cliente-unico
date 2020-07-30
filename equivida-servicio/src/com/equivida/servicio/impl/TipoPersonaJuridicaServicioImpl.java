package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoPersonaJuridicaDao;
import com.equivida.modelo.TipoPersonaJuridica;
import com.equivida.servicio.TipoPersonaJuridicaServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoPersonaJuridicaServicio")
public class TipoPersonaJuridicaServicioImpl extends
		GenericServiceImpl<TipoPersonaJuridica, Short> implements
		TipoPersonaJuridicaServicio {

	@EJB
	private TipoPersonaJuridicaDao tipoPersonaJuridicaDao;

	public GenericDao<TipoPersonaJuridica, Short> getDao() {
		return tipoPersonaJuridicaDao;
	}
}
