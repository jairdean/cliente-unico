package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.CiudadDao;
import com.equivida.modelo.Ciudad;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "CiudadDao")
public class CiudadDaoEjb extends GenericDaoEjb<Ciudad, Short> implements
		CiudadDao {

	public CiudadDaoEjb() {
		super(Ciudad.class);
	}

}
