package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.HistoriaMedicaFamiliarDao;
import com.equivida.modelo.HistoriaMedicaFamiliar;
import com.equivida.servicio.HistoriaMedicaFamiliarServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "HistoriaMedicaFamiliarServicio")
public class HistoriaMedicaFamiliarServicioImpl extends
		GenericServiceImpl<HistoriaMedicaFamiliar, Integer> implements
		HistoriaMedicaFamiliarServicio {

	@EJB
	private HistoriaMedicaFamiliarDao historiaMedicaFamiliarDao;

	public GenericDao<HistoriaMedicaFamiliar, Integer> getDao() {
		return historiaMedicaFamiliarDao;
	}
}
