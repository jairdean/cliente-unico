package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.PerfilFinancieroNaturalDao;
import com.equivida.modelo.PerfilFinancieroNatural;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "PerfilFinancieroNaturalDao")
public class PerfilFinancieroNaturalDaoEjb extends
		GenericDaoEjb<PerfilFinancieroNatural, Integer> implements
		PerfilFinancieroNaturalDao {

	public PerfilFinancieroNaturalDaoEjb() {
		super(PerfilFinancieroNatural.class);
	}

}
