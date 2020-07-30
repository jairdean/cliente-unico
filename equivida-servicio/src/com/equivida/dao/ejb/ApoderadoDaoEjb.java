package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.ApoderadoDao;
import com.equivida.modelo.Apoderado;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "ApoderadoDao")
public class ApoderadoDaoEjb extends GenericDaoEjb<Apoderado, Integer>
		implements ApoderadoDao {

	public ApoderadoDaoEjb() {
		super(Apoderado.class);
	}

}
