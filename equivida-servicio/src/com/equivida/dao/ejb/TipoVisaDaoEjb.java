package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoVisaDao;
import com.equivida.modelo.TipoVisa;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "TipoVisaDao")
public class TipoVisaDaoEjb extends GenericDaoEjb<TipoVisa, Short>
		implements TipoVisaDao {

	public TipoVisaDaoEjb() {
		super(TipoVisa.class);
	}
}