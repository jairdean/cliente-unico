package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.IngresoMensualAdicionalDao;
import com.equivida.modelo.IngresoMensualAdicional;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "IngresoMensualAdicionalDao")
public class IngresoMensualAdicionalDaoEjb extends
		GenericDaoEjb<IngresoMensualAdicional, Integer> implements
		IngresoMensualAdicionalDao {

	public IngresoMensualAdicionalDaoEjb() {
		super(IngresoMensualAdicional.class);
	}

}
