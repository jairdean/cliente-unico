package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.SeguroVigenteDao;
import com.equivida.modelo.SeguroVigente;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "SeguroVigenteDao")
public class SeguroVigenteDaoEjb extends
		GenericDaoEjb<SeguroVigente, Integer> implements SeguroVigenteDao {

	public SeguroVigenteDaoEjb() {
		super(SeguroVigente.class);
	}

}