package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.MedicoConsultadoDao;
import com.equivida.modelo.MedicoConsultado;
import com.equivida.servicio.MedicoConsultadoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "MedicoConsultadoServicio")
public class MedicoConsultadoServicioImpl extends
		GenericServiceImpl<MedicoConsultado, Integer> implements
		MedicoConsultadoServicio {

	@EJB
	private MedicoConsultadoDao medicoConsultadoDao;

	public GenericDao<MedicoConsultado, Integer> getDao() {
		return medicoConsultadoDao;
	}
}
