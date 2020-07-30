package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.OtroEmpleoDao;
import com.equivida.modelo.OtroEmpleo;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "OtroEmpleoDao")
public class OtroEmpleoDaoEjb extends GenericDaoEjb<OtroEmpleo, Integer>
		implements OtroEmpleoDao {

	public OtroEmpleoDaoEjb() {
		super(OtroEmpleo.class);
	}

}
