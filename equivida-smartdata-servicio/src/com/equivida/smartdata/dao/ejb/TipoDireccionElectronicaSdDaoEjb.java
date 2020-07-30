/**
*TipoDireccionElectronicaDaoEjb.java
*
*Tue Jan 26 12:48:34 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TipoDireccionElectronicaSdDao;
import com.equivida.smartdata.model.TipoDireccionElectronicaSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "TipoDireccionElectronicaSdDao")
public class TipoDireccionElectronicaSdDaoEjb extends GenericDaoEjb<TipoDireccionElectronicaSd, Short>
		implements TipoDireccionElectronicaSdDao {

	public TipoDireccionElectronicaSdDaoEjb() {
		super(TipoDireccionElectronicaSd.class);
	}
}