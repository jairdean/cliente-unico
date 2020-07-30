package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoActividadDao;
import com.equivida.modelo.TipoActividad;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "TipoActividadDao")
public class TipoActividadDaoEjb extends GenericDaoEjb<TipoActividad, Short>
		implements TipoActividadDao {

	public TipoActividadDaoEjb() {
		super(TipoActividad.class);
	}

}
