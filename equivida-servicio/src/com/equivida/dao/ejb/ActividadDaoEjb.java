package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.ActividadDao;
import com.equivida.modelo.Actividad;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "ActividadDao")
public class ActividadDaoEjb extends GenericDaoEjb<Actividad, Integer>
		implements ActividadDao {

	public ActividadDaoEjb() {
		super(Actividad.class);
	}

}
