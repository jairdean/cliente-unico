package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.CompaniaSeguroDao;
import com.equivida.modelo.CompaniaSeguro;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "CompaniaSeguroDao")
public class CompaniaSeguroDaoEjb extends GenericDaoEjb<CompaniaSeguro, Short>
		implements CompaniaSeguroDao {

	public CompaniaSeguroDaoEjb() {
		super(CompaniaSeguro.class);
	}
}