package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.BiometricaDao;
import com.equivida.modelo.Biometrica;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "BiometricaDao")
public class BiometricaDaoEjb extends GenericDaoEjb<Biometrica, Integer>
		implements BiometricaDao {

	public BiometricaDaoEjb() {
		super(Biometrica.class);
	}

}
