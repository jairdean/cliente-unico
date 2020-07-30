package com.equivida.homologacion.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.homologacion.dao.TelefonoEquividaDao;
import com.equivida.homologacion.modelo.TelefonoEquivida;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "TelefonoEquividaDao")
public class TelefonoEquividaDaoEjb extends
		GenericDaoEjb<TelefonoEquivida, Integer> implements TelefonoEquividaDao {

	public TelefonoEquividaDaoEjb() {
		super(TelefonoEquivida.class);
	}

}