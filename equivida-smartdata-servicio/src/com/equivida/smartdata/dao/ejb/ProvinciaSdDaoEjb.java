/**
*ProvinciaDaoEjb.java
*
*Tue Jan 26 12:44:41 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.ProvinciaSdDao;
import com.equivida.smartdata.model.ProvinciaSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "ProvinciaSdDao")
public class ProvinciaSdDaoEjb extends GenericDaoEjb<ProvinciaSd, Short> implements ProvinciaSdDao {

	public ProvinciaSdDaoEjb() {
		super(ProvinciaSd.class);
	}
}