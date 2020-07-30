/**
*EmpleoDependienteDaoEjb.java
*
*Wed Feb 17 12:55:38 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.EmpleoDependienteSdDao;
import com.equivida.smartdata.model.EmpleoDependienteSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "EmpleoDependienteSdDao")
public class EmpleoDependienteSdDaoEjb extends GenericDaoEjb<EmpleoDependienteSd, Integer> implements EmpleoDependienteSdDao {

	public EmpleoDependienteSdDaoEjb() {
		super(EmpleoDependienteSd.class);
	}
}