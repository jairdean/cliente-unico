/**
*TipoParentescoRelacionDaoEjb.java
*
*Tue Jan 26 12:51:11 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TipoParentescoRelacionSdDao;
import com.equivida.smartdata.model.TipoParentescoRelacionSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "TipoParentescoRelacionSdDao")
public class TipoParentescoRelacionSdDaoEjb extends GenericDaoEjb<TipoParentescoRelacionSd, Short>
		implements TipoParentescoRelacionSdDao {

	public TipoParentescoRelacionSdDaoEjb() {
		super(TipoParentescoRelacionSd.class);
	}
}