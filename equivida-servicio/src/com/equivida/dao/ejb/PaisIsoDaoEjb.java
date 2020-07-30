package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.PaisIsoDao;
import com.equivida.modelo.PaisIso;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "PaisIsoIsoDao")
public class PaisIsoDaoEjb extends GenericDaoEjb<PaisIso, Short> implements
		PaisIsoDao {

	public PaisIsoDaoEjb() {
		super(PaisIso.class);
	}

}
