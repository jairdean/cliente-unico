/**
*EstadoCivilDaoEjb.java
*
*Tue Jan 26 12:35:33 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.EstadoCivilSdDao;
import com.equivida.smartdata.model.EstadoCivilSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "EstadoCivilSdDao")
public class EstadoCivilSdDaoEjb extends GenericDaoEjb<EstadoCivilSd, Short> implements EstadoCivilSdDao {

	public EstadoCivilSdDaoEjb() {
		super(EstadoCivilSd.class);
	}
}