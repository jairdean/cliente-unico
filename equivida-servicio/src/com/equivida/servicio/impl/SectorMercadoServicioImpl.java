package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.SectorMercadoDao;
import com.equivida.modelo.SectorMercado;
import com.equivida.servicio.SectorMercadoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Duval
 */
@Stateless(name = "SectorMercadoServicio")
public class SectorMercadoServicioImpl extends
		GenericServiceImpl<SectorMercado, Integer> implements SectorMercadoServicio {

	@EJB
	private SectorMercadoDao sectorMercadoDao;

	public GenericDao<SectorMercado, Integer> getDao() {
		return sectorMercadoDao;
	}
}
