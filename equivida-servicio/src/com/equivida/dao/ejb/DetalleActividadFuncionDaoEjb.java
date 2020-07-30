package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.DetalleActividadFuncionDao;
import com.equivida.modelo.DetalleActividadFuncion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "DetalleActividadFuncionDao")
public class DetalleActividadFuncionDaoEjb extends
		GenericDaoEjb<DetalleActividadFuncion, Integer> implements
		DetalleActividadFuncionDao {

	public DetalleActividadFuncionDaoEjb() {
		super(DetalleActividadFuncion.class);
	}

}
