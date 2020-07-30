/**
*ActividadEconomicaNivelDaoEjb.java
*
*Tue Jan 26 12:18:50 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.ActividadEconomicaSdDao;
import com.equivida.smartdata.model.ActividadEconomicaSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "ActividadEconomicaSdDao")
public class ActividadEconomicaSdDaoEjb extends GenericDaoEjb<ActividadEconomicaSd, Short>
		implements ActividadEconomicaSdDao {

	public ActividadEconomicaSdDaoEjb() {
		super(ActividadEconomicaSd.class);
	}
}