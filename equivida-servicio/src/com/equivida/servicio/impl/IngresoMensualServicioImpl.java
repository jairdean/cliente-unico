package com.equivida.servicio.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.IngresoMensualDao;
import com.equivida.modelo.IngresoMensual;
import com.equivida.servicio.IngresoMensualServicio;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "IngresoMensualServicio")
public class IngresoMensualServicioImpl extends GenericServiceImpl<IngresoMensual, Integer>
		implements IngresoMensualServicio {

	@EJB
	private IngresoMensualDao ingresoMensualDao;

	public GenericDao<IngresoMensual, Integer> getDao() {
		return ingresoMensualDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.servicio.IngresoMensualServicio#persistir(com.equivida.modelo.
	 * IngresoMensual)
	 */
	@Override
	public void persistir(IngresoMensual ingresoMensual) {
		System.out.println("ingresoMensual");
		System.out.println(ingresoMensual.toString());
		if (ingresoMensual.getSecIngresoPersona() == null) {
			// create(ingresoMensual);
			// ingresoMensualDao.insertarJdbc(ingresoMensual);
			// se debe guardar solito, puesto q esta como cascade
		} else {
			update(ingresoMensual);
		}
	}
}
