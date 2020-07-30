package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.OtroEmpleoDao;
import com.equivida.modelo.OtroEmpleo;
import com.equivida.servicio.OtroEmpleoServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "OtroEmpleoServicio")
public class OtroEmpleoServicioImpl extends
		GenericServiceImpl<OtroEmpleo, Integer> implements OtroEmpleoServicio {

	@EJB
	private OtroEmpleoDao otroEmpleoDao;

	public GenericDao<OtroEmpleo, Integer> getDao() {
		return otroEmpleoDao;
	}
}
