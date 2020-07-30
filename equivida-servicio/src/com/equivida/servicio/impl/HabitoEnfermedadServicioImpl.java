package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.HabitoEnfermedadDao;
import com.equivida.modelo.HabitoEnfermedad;
import com.equivida.servicio.HabitoEnfermedadServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "HabitoEnfermedadServicio")
public class HabitoEnfermedadServicioImpl extends
		GenericServiceImpl<HabitoEnfermedad, Integer> implements
		HabitoEnfermedadServicio {

	@EJB
	private HabitoEnfermedadDao habitoEnfermedadDao;

	public GenericDao<HabitoEnfermedad, Integer> getDao() {
		return habitoEnfermedadDao;
	}
}
