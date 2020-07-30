package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.CategoriaPpeDao;
import com.equivida.modelo.CategoriaPpe;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "CategoriaPpeDao")
public class CategoriaPpeDaoEjb extends GenericDaoEjb<CategoriaPpe, Short>
		implements CategoriaPpeDao {

	public CategoriaPpeDaoEjb() {
		super(CategoriaPpe.class);
	}

}
