package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.ReferenciaComercialDao;
import com.equivida.modelo.ReferenciaComercial;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "ReferenciaComercialDao")
public class ReferenciaComercialDaoEjb extends
		GenericDaoEjb<ReferenciaComercial, Integer> implements
		ReferenciaComercialDao {

	public ReferenciaComercialDaoEjb() {
		super(ReferenciaComercial.class);
	}

}
