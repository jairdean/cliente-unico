/**
*RelacionDaoEjb.java
*
*Thu Feb 18 10:46:12 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.RelacionSdDao;
import com.equivida.smartdata.model.RelacionSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "RelacionSdDao")
public class RelacionSdDaoEjb extends GenericDaoEjb<RelacionSd, Integer> implements RelacionSdDao {

	public RelacionSdDaoEjb() {
		super(RelacionSd.class);
	}
}