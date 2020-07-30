package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.EmpleoIndependienteDao;
import com.equivida.modelo.EmpleoIndependiente;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "EmpleoIndependienteDao")
public class EmpleoIndependienteDaoEjb extends
		GenericDaoEjb<EmpleoIndependiente, Integer> implements
		EmpleoIndependienteDao {

	public EmpleoIndependienteDaoEjb() {
		super(EmpleoIndependiente.class);
	}

}