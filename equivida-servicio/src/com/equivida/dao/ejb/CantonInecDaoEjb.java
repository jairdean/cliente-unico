package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.CantonInecDao;
import com.equivida.modelo.CantonInec;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "CantonInecInecDao")
public class CantonInecDaoEjb extends GenericDaoEjb<CantonInec, Short>
		implements CantonInecDao {

	public CantonInecDaoEjb() {
		super(CantonInec.class);
	}

}
