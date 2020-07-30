package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.InformacionAdicionalDao;
import com.equivida.modelo.InformacionAdicional;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "InformacionAdicionalDao")
public class InformacionAdicionalDaoEjb extends
		GenericDaoEjb<InformacionAdicional, Integer> implements
		InformacionAdicionalDao {

	public InformacionAdicionalDaoEjb() {
		super(InformacionAdicional.class);
	}

}
