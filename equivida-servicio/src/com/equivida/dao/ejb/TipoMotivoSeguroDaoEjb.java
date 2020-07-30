package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoMotivoSeguroDao;
import com.equivida.modelo.TipoMotivoSeguro;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "TipoMotivoSeguroDao")
public class TipoMotivoSeguroDaoEjb extends
		GenericDaoEjb<TipoMotivoSeguro, Short> implements TipoMotivoSeguroDao {

	public TipoMotivoSeguroDaoEjb() {
		super(TipoMotivoSeguro.class);
	}

}
