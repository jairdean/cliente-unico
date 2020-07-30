package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.DireccionElectronicaDao;
import com.equivida.modelo.DireccionElectronica;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "DireccionElectronicaDao")
public class DireccionElectronicaDaoEjb extends
		GenericDaoEjb<DireccionElectronica, Integer> implements
		DireccionElectronicaDao {

	public DireccionElectronicaDaoEjb() {
		super(DireccionElectronica.class);
	}

}
