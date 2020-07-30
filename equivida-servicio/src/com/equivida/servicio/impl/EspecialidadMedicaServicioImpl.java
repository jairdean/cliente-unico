package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.EspecialidadMedicaDao;
import com.equivida.modelo.EspecialidadMedica;
import com.equivida.servicio.EspecialidadMedicaServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "EspecialidadMedicaServicio")
public class EspecialidadMedicaServicioImpl extends
		GenericServiceImpl<EspecialidadMedica, Short> implements
		EspecialidadMedicaServicio {

	@EJB
	private EspecialidadMedicaDao especialidadMedicaDao;

	public GenericDao<EspecialidadMedica, Short> getDao() {
		return especialidadMedicaDao;
	}
}
