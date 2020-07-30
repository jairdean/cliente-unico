package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.ProvinciaInecDao;
import com.equivida.modelo.ProvinciaInec;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "ProvinciaInecInecDao")
public class ProvinciaInecDaoEjb extends GenericDaoEjb<ProvinciaInec, Short>
		implements ProvinciaInecDao {

	public ProvinciaInecDaoEjb() {
		super(ProvinciaInec.class);
	}

}
