/**
*CantonDaoEjb.java
*
*Tue Jan 26 12:31:00 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.CantonSdDao;
import com.equivida.smartdata.model.CantonSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "CantonSdDao")
public class CantonSdDaoEjb extends GenericDaoEjb<CantonSd, Short> implements CantonSdDao {

	public CantonSdDaoEjb() {
		super(CantonSd.class);
	}
}