/**
*DetalleRelacionDaoEjb.java
*
*Tue Jan 26 12:32:38 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import javax.ejb.Stateless;

import com.equivida.smartdata.dao.DetalleRelacionSdDao;
import com.equivida.smartdata.model.DetalleRelacionSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "DetalleRelacionSdDao")
public class DetalleRelacionSdDaoEjb extends GenericDaoEjb<DetalleRelacionSd, Short> implements DetalleRelacionSdDao {

	public DetalleRelacionSdDaoEjb() {
		super(DetalleRelacionSd.class);
	}
}