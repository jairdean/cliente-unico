package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.IngresoAnualDao;
import com.equivida.modelo.IngresoAnual;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "IngresoAnualDao")
public class IngresoAnualDaoEjb extends GenericDaoEjb<IngresoAnual, Integer>
		implements IngresoAnualDao {

	public IngresoAnualDaoEjb() {
		super(IngresoAnual.class);
	}

}
