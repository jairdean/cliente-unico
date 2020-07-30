package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoOtroSeguroDao;
import com.equivida.modelo.TipoOtroSeguro;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "TipoOtroSeguroDao")
public class TipoOtroSeguroDaoEjb extends GenericDaoEjb<TipoOtroSeguro, Short>
		implements TipoOtroSeguroDao {

	public TipoOtroSeguroDaoEjb() {
		super(TipoOtroSeguro.class);
	}

}
