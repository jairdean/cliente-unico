package com.equivida.homologacion.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.homologacion.dao.DireccionElectronicaEquividaDao;
import com.equivida.homologacion.modelo.DireccionElectronicaEquivida;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "DireccionElectronicaEquividaDao")
public class DireccionElectronicaEquividaDaoEjb extends
		GenericDaoEjb<DireccionElectronicaEquivida, Integer> implements
		DireccionElectronicaEquividaDao {

	public DireccionElectronicaEquividaDaoEjb() {
		super(DireccionElectronicaEquivida.class);
	}
}