package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.ReferenciaBancariaDao;
import com.equivida.modelo.ReferenciaBancaria;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "ReferenciaBancariaDao")
public class ReferenciaBancariaDaoEjb extends
		GenericDaoEjb<ReferenciaBancaria, Integer> implements
		ReferenciaBancariaDao {

	public ReferenciaBancariaDaoEjb() {
		super(ReferenciaBancaria.class);
	}

}
