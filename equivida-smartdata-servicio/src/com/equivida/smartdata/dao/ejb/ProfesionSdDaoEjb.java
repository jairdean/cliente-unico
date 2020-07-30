/**
*ProfesionDaoEjb.java
*
*Tue Jan 26 12:43:26 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.ProfesionSdDao;
import com.equivida.smartdata.model.ProfesionSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "ProfesionSdDao")
public class ProfesionSdDaoEjb extends GenericDaoEjb<ProfesionSd, Short> implements ProfesionSdDao {

	public ProfesionSdDaoEjb() {
		super(ProfesionSd.class);
	}
}