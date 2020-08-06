/**
 *DireccionDaoEjb.java
 *
 *Wed Aug 05 19:07:12 ECT 2020
 */
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.DireccionElectronicaSdDao;
import com.equivida.smartdata.model.DireccionElectronicaSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "DireccionElectronicaSdDao")
public class DireccionElectronicaSdDaoEjb extends GenericDaoEjb<DireccionElectronicaSd, Integer>
		implements DireccionElectronicaSdDao {

	public DireccionElectronicaSdDaoEjb() {
		super(DireccionElectronicaSd.class);
	}

}