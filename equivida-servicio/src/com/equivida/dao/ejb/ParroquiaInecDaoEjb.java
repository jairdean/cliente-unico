package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.ParroquiaInecDao;
import com.equivida.modelo.ParroquiaInec;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "ParroquiaInecInecDao")
public class ParroquiaInecDaoEjb extends GenericDaoEjb<ParroquiaInec, Short>
		implements ParroquiaInecDao {

	public ParroquiaInecDaoEjb() {
		super(ParroquiaInec.class);
	}

}
