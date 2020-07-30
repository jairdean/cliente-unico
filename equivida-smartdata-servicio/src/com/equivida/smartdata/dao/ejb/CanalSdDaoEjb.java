/**
*CanalDaoEjb.java
*
*Tue Jan 26 12:28:45 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.CanalSdDao;
import com.equivida.smartdata.model.CanalSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "CanalSdDao")
public class CanalSdDaoEjb extends GenericDaoEjb<CanalSd, Short> implements CanalSdDao {

	public CanalSdDaoEjb() {
		super(CanalSd.class);
	}
}