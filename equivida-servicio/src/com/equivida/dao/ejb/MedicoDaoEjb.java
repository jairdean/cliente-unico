package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.MedicoDao;
import com.equivida.modelo.Medico;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "MedicoDao")
public class MedicoDaoEjb extends GenericDaoEjb<Medico, Integer> implements
		MedicoDao {

	public MedicoDaoEjb() {
		super(Medico.class);
	}

}
