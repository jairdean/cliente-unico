package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.MedicoConsultadoDao;
import com.equivida.modelo.MedicoConsultado;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "MedicoConsultadoDao")
public class MedicoConsultadoDaoEjb extends
		GenericDaoEjb<MedicoConsultado, Integer> implements MedicoConsultadoDao {

	public MedicoConsultadoDaoEjb() {
		super(MedicoConsultado.class);
	}

}
