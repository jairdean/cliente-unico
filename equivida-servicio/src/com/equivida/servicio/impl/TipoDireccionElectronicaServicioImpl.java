package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.TipoDireccionElectronicaDao;
import com.equivida.modelo.TipoDireccionElectronica;
import com.equivida.servicio.TipoDireccionElectronicaServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoDireccionElectronicaServicio")
public class TipoDireccionElectronicaServicioImpl extends
		GenericServiceImpl<TipoDireccionElectronica, Short> implements
		TipoDireccionElectronicaServicio {

	@EJB
	private TipoDireccionElectronicaDao tipoDireccionElectronicaDao;

	public GenericDao<TipoDireccionElectronica, Short> getDao() {
		return tipoDireccionElectronicaDao;
	}
}
