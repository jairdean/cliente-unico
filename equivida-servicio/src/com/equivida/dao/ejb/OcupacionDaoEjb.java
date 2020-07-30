package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.OcupacionDao;
import com.equivida.modelo.Ocupacion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "OcupacionDao")
public class OcupacionDaoEjb extends GenericDaoEjb<Ocupacion, Short> implements
		OcupacionDao {

	public OcupacionDaoEjb() {
		super(Ocupacion.class);
	}

}
