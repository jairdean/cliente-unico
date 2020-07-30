package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.SeguroAdicionalDao;
import com.equivida.modelo.SeguroAdicional;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "SeguroAdicionalDao")
public class SeguroAdicionalDaoEjb extends
		GenericDaoEjb<SeguroAdicional, Integer> implements SeguroAdicionalDao {

	public SeguroAdicionalDaoEjb() {
		super(SeguroAdicional.class);
	}

}
