package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.SeguroVigenteDao;
import com.equivida.modelo.SeguroVigente;
import com.equivida.servicio.SeguroVigenteServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "SeguroVigenteServicio")
public class SeguroVigenteServicioImpl extends
		GenericServiceImpl<SeguroVigente, Integer> implements
		SeguroVigenteServicio {

	@EJB
	private SeguroVigenteDao seguroVigenteDao;

	public GenericDao<SeguroVigente, Integer> getDao() {
		return seguroVigenteDao;
	}
}
