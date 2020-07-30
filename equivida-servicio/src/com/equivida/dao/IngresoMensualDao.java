package com.equivida.dao;

import com.equivida.modelo.IngresoMensual;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
public interface IngresoMensualDao extends GenericDao<IngresoMensual, Integer> {

	void insertarJdbc(IngresoMensual ingresoMensual);

}
