package com.equivida.homologacion.dao;

import javax.ejb.Local;

import com.equivida.homologacion.modelo.TelefonoEquivida;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface TelefonoEquividaDao extends
		GenericDao<TelefonoEquivida, Integer> {

}