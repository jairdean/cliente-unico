package com.equivida.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.dao.RamoSeguroDao;
import com.equivida.modelo.RamoSeguro;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "RamoSeguroDao")
public class RamoSeguroDaoEjb extends GenericDaoEjb<RamoSeguro, Short>
		implements RamoSeguroDao {

	public RamoSeguroDaoEjb() {
		super(RamoSeguro.class);
	}
}