package com.equivida.homologacion.dao;

import javax.ejb.Local;

import com.equivida.homologacion.modelo.DireccionEquivida;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface DireccionEquividaDao extends
		GenericDao<DireccionEquivida, Integer> {

}