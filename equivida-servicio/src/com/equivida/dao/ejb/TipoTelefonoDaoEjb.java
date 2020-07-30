package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoTelefonoDao;
import com.equivida.modelo.TipoTelefono;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "TipoTelefonoDao")
public class TipoTelefonoDaoEjb extends GenericDaoEjb<TipoTelefono, Short>
		implements TipoTelefonoDao {

	public TipoTelefonoDaoEjb() {
		super(TipoTelefono.class);
	}

}
