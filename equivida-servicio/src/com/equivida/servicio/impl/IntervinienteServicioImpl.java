package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.IntervinienteDao;
import com.equivida.modelo.Interviniente;
import com.equivida.servicio.IntervinienteServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "IntervinienteServicio")
public class IntervinienteServicioImpl extends
		GenericServiceImpl<Interviniente, Integer> implements
		IntervinienteServicio {

	@EJB
	private IntervinienteDao intervinienteDao;

	public GenericDao<Interviniente, Integer> getDao() {
		return intervinienteDao;
	}
}
