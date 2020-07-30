package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoOtraOcupacionDao;
import com.equivida.modelo.TipoOtraOcupacion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "TipoOtraOcupacionDao")
public class TipoOtraOcupacionDaoEjb extends
		GenericDaoEjb<TipoOtraOcupacion, Short> implements TipoOtraOcupacionDao {

	public TipoOtraOcupacionDaoEjb() {
		super(TipoOtraOcupacion.class);
	}

}
