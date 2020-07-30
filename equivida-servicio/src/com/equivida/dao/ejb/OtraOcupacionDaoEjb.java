package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.OtraOcupacionDao;
import com.equivida.modelo.OtraOcupacion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "OtraOcupacionDao")
public class OtraOcupacionDaoEjb extends GenericDaoEjb<OtraOcupacion, Integer>
		implements OtraOcupacionDao {

	public OtraOcupacionDaoEjb() {
		super(OtraOcupacion.class);
	}
}