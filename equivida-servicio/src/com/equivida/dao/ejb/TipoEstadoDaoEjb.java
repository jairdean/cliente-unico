package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoEstadoDao;
import com.equivida.modelo.TipoEstado;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "TipoEstadoDao")
public class TipoEstadoDaoEjb extends GenericDaoEjb<TipoEstado, Short>
		implements TipoEstadoDao {

	public TipoEstadoDaoEjb() {
		super(TipoEstado.class);
	}

}
