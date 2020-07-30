package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.BeneficiarioDao;
import com.equivida.modelo.Beneficiario;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "BeneficiarioDao")
public class BeneficiarioDaoEjb extends GenericDaoEjb<Beneficiario, Integer>
		implements BeneficiarioDao {

	public BeneficiarioDaoEjb() {
		super(Beneficiario.class);
	}

}
