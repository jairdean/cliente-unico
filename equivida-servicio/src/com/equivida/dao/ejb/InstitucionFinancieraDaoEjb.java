package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.InstitucionFinancieraDao;
import com.equivida.modelo.InstitucionFinanciera;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "InstitucionFinancieraDao")
public class InstitucionFinancieraDaoEjb extends
		GenericDaoEjb<InstitucionFinanciera, Short> implements
		InstitucionFinancieraDao {

	public InstitucionFinancieraDaoEjb() {
		super(InstitucionFinanciera.class);
	}

}
