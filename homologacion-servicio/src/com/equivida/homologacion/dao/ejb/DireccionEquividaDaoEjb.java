package com.equivida.homologacion.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.homologacion.dao.DireccionEquividaDao;
import com.equivida.homologacion.modelo.DireccionEquivida;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "DireccionEquividaDao")
public class DireccionEquividaDaoEjb extends
		GenericDaoEjb<DireccionEquivida, Integer> implements DireccionEquividaDao {

	public DireccionEquividaDaoEjb() {
		super(DireccionEquivida.class);
	}

}