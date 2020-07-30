package com.equivida.dao;

import java.util.Collection;

import com.equivida.modelo.BeneficiarioPoliza;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * @author Daniel Cardenas
 * @author Gerardo Tuquerrez
 * 
 */
public interface BeneficiarioPolizaDao extends
		GenericDao<BeneficiarioPoliza, Integer> {

	Collection<BeneficiarioPoliza> obtenerPorSecPersona(Integer secPersona);
	
}
