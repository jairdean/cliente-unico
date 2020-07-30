package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.DetallePasaporteDao;
import com.equivida.modelo.DetallePasaporte;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "DetallePasaporteDao")
public class DetallePasaporteDaoEjb extends
		GenericDaoEjb<DetallePasaporte, Integer> implements DetallePasaporteDao {

	public DetallePasaporteDaoEjb() {
		super(DetallePasaporte.class);
	}

}