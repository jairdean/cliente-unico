package com.equivida.dao;

import com.equivida.modelo.Direccion;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
public interface DireccionDao extends GenericDao<Direccion, Integer> {

	void updateLight(Direccion direccion);
}