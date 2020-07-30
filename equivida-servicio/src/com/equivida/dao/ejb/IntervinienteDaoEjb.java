package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.IntervinienteDao;
import com.equivida.modelo.Interviniente;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "IntervinienteDao")
public class IntervinienteDaoEjb extends GenericDaoEjb<Interviniente, Integer>
		implements IntervinienteDao {

	public IntervinienteDaoEjb() {
		super(Interviniente.class);
	}

}
