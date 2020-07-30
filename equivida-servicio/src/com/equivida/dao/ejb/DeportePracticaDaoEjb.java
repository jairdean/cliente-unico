package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.DeportePracticaDao;
import com.equivida.modelo.DeportePractica;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "DeportePracticaDao")
public class DeportePracticaDaoEjb extends
		GenericDaoEjb<DeportePractica, Integer> implements DeportePracticaDao {

	public DeportePracticaDaoEjb() {
		super(DeportePractica.class);
	}

}
