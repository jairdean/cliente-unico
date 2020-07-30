package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.HijoDao;
import com.equivida.modelo.Hijo;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Duval
 * 
 */
@Stateless(name = "HijoDao")
public class HijoDaoEjb extends GenericDaoEjb<Hijo, Integer>
		implements HijoDao {

	public HijoDaoEjb() {
		super(Hijo.class);
	}

}
