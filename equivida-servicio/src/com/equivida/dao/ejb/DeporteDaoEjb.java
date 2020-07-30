package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.DeporteDao;
import com.equivida.modelo.Deporte;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "DeporteDao")
public class DeporteDaoEjb extends GenericDaoEjb<Deporte, Short> implements
		DeporteDao {

	public DeporteDaoEjb() {
		super(Deporte.class);
	}

}
