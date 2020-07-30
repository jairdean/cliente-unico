package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.MedicoDao;
import com.equivida.modelo.Medico;
import com.equivida.servicio.MedicoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "MedicoServicio")
public class MedicoServicioImpl extends GenericServiceImpl<Medico, Integer>
		implements MedicoServicio {

	@EJB
	private MedicoDao medicoDao;

	public GenericDao<Medico, Integer> getDao() {
		return medicoDao;
	}
}
