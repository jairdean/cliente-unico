package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.TipoDireccionElectronicaDao;
import com.equivida.modelo.TipoDireccionElectronica;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "TipoDireccionElectronicaDao")
public class TipoDireccionElectronicaDaoEjb extends
		GenericDaoEjb<TipoDireccionElectronica, Short> implements
		TipoDireccionElectronicaDao {

	public TipoDireccionElectronicaDaoEjb() {
		super(TipoDireccionElectronica.class);
	}

}