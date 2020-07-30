package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.CategoriaPpeDao;
import com.equivida.modelo.CategoriaPpe;
import com.equivida.servicio.CategoriaPpeServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "CategoriaPpeServicio")
public class CategoriaPpeServicioImpl extends
		GenericServiceImpl<CategoriaPpe, Short> implements CategoriaPpeServicio {

	@EJB
	private CategoriaPpeDao categoriaPpeDao;

	public GenericDao<CategoriaPpe, Short> getDao() {
		return categoriaPpeDao;
	}
}
