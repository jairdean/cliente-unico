package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.MotivoSeguroDao;
import com.equivida.modelo.MotivoSeguro;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "MotivoSeguroDao")
public class MotivoSeguroDaoEjb extends GenericDaoEjb<MotivoSeguro, Integer>
		implements MotivoSeguroDao {

	public MotivoSeguroDaoEjb() {
		super(MotivoSeguro.class);
	}

}
