package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.EspecialidadMedicaDao;
import com.equivida.modelo.EspecialidadMedica;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "EspecialidadMedicaDao")
public class EspecialidadMedicaDaoEjb extends
		GenericDaoEjb<EspecialidadMedica, Short> implements
		EspecialidadMedicaDao {

	public EspecialidadMedicaDaoEjb() {
		super(EspecialidadMedica.class);
	}

}
