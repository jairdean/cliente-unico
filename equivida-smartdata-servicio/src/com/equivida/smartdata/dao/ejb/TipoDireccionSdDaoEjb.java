/**
*TipoDireccionDaoEjb.java
*
*Tue Jan 26 12:47:28 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TipoDireccionSdDao;
import com.equivida.smartdata.model.TipoDireccionSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "TipoDireccionSdDao")
public class TipoDireccionSdDaoEjb extends GenericDaoEjb<TipoDireccionSd, Short> implements TipoDireccionSdDao {

	public TipoDireccionSdDaoEjb() {
		super(TipoDireccionSd.class);
	}
}