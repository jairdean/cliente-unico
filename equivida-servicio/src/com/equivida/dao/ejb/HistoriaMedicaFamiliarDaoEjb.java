package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.HistoriaMedicaFamiliarDao;
import com.equivida.modelo.HistoriaMedicaFamiliar;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "HistoriaMedicaFamiliarDao")
public class HistoriaMedicaFamiliarDaoEjb extends
		GenericDaoEjb<HistoriaMedicaFamiliar, Integer> implements
		HistoriaMedicaFamiliarDao {

	public HistoriaMedicaFamiliarDaoEjb() {
		super(HistoriaMedicaFamiliar.class);
	}

}
