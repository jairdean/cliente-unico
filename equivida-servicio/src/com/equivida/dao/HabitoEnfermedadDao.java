package com.equivida.dao;

import com.equivida.modelo.HabitoEnfermedad;
import com.saviasoft.persistence.util.dao.GenericDao;

/**
 * 
 * @author Gerardo Tuquerrez
 * 
 */
public interface HabitoEnfermedadDao extends
		GenericDao<HabitoEnfermedad, Integer> {

	void updateLight(HabitoEnfermedad habitoEnfermedad);

}
