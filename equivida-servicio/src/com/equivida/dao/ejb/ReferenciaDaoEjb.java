package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.ReferenciaDao;
import com.equivida.modelo.Referencia;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "ReferenciaDao")
public class ReferenciaDaoEjb extends GenericDaoEjb<Referencia, Integer>
		implements ReferenciaDao {

	public ReferenciaDaoEjb() {
		super(Referencia.class);
	}

}
