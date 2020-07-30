package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoRiesgoDao;
import com.equivida.modelo.TipoRiesgo;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "TipoRiesgoDao")
public class TipoRiesgoDaoEjb extends GenericDaoEjb<TipoRiesgo, Short>
		implements TipoRiesgoDao {

	public TipoRiesgoDaoEjb() {
		super(TipoRiesgo.class);
	}

}
