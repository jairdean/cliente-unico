/**
*TipoTelefonoDaoEjb.java
*
*Tue Jan 26 12:52:47 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TipoTelefonoSdDao;
import com.equivida.smartdata.model.TipoTelefonoSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "TipoTelefonoSdDao")
public class TipoTelefonoSdDaoEjb extends GenericDaoEjb<TipoTelefonoSd, Short> implements TipoTelefonoSdDao {

	public TipoTelefonoSdDaoEjb() {
		super(TipoTelefonoSd.class);
	}
}