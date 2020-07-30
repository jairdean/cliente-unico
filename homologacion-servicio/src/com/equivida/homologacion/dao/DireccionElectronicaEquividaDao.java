package com.equivida.homologacion.dao;

import javax.ejb.Local;

import com.equivida.homologacion.modelo.DireccionElectronicaEquivida;
import com.saviasoft.persistence.util.dao.GenericDao;

@Local
public interface DireccionElectronicaEquividaDao extends
		GenericDao<DireccionElectronicaEquivida, Integer> {

}