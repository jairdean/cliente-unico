package com.equivida.dao;

import com.equivida.modelo.ActividadEconomica;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
public interface ActividadEconomicaDao extends
		GenericDao<ActividadEconomica, Short> {

	String obtenerCiiuParaSise(Short codActividadEconomica);
	
}
