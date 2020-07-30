/**
*PaisDaoEjb.java
*
*Tue Jan 26 12:37:15 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.PaisSdDao;
import com.equivida.smartdata.model.PaisSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "PaisSdDao")
public class PaisSdDaoEjb extends GenericDaoEjb<PaisSd, Short> implements PaisSdDao {

	public PaisSdDaoEjb() {
		super(PaisSd.class);
	}
}