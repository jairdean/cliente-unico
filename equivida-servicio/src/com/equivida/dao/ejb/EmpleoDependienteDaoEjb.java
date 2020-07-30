package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.EmpleoDependienteDao;
import com.equivida.modelo.EmpleoDependiente;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "EmpleoDependienteDao")
public class EmpleoDependienteDaoEjb extends
		GenericDaoEjb<EmpleoDependiente, Integer> implements
		EmpleoDependienteDao {

	public EmpleoDependienteDaoEjb() {
		super(EmpleoDependiente.class);
	}

}