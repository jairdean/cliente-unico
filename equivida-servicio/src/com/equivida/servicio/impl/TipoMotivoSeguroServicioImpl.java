package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoMotivoSeguroDao;
import com.equivida.modelo.TipoMotivoSeguro;
import com.equivida.servicio.TipoMotivoSeguroServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoMotivoSeguroServicio")
public class TipoMotivoSeguroServicioImpl extends
		GenericServiceImpl<TipoMotivoSeguro, Short> implements
		TipoMotivoSeguroServicio {

	@EJB
	private TipoMotivoSeguroDao tipoMotivoSeguroDao;

	public GenericDao<TipoMotivoSeguro, Short> getDao() {
		return tipoMotivoSeguroDao;
	}
}
