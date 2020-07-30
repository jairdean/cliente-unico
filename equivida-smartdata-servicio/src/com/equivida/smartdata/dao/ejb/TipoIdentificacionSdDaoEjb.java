/**
*TipoIdentificacionDaoEjb.java
*
*Tue Jan 26 12:49:53 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.TipoIdentificacionSdDao;
import com.equivida.smartdata.model.TipoIdentificacionSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "TipoIdentificacionSdDao")
public class TipoIdentificacionSdDaoEjb extends GenericDaoEjb<TipoIdentificacionSd, Character>
		implements TipoIdentificacionSdDao {

	public TipoIdentificacionSdDaoEjb() {
		super(TipoIdentificacionSd.class);
	}
}