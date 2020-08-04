/**
 *PersonaNaturalDaoEjb.java
 *
 *Tue Jan 26 12:42:07 ECT 2016
 */
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.InformacionAdicionalSdDao;
import com.equivida.smartdata.model.InformacionAdicionalSd;
import com.equivida.smartdata.model.PersonaSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "InformacionAdicionalSdDao")
public class InformacionAdicionalSdDaoEjb extends GenericDaoEjb<InformacionAdicionalSd, Integer> implements InformacionAdicionalSdDao {

	public InformacionAdicionalSdDaoEjb() {
		super(InformacionAdicionalSd.class);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.InformacionAdicionalSdDao#crearInformacionAdicional
	 * (InformacionAdicionalSd)
	 */
	@Override
	public boolean crearInformacionAdicional(InformacionAdicionalSd informacionAdicionalSd) {
/*
		String hql = "INSERT INTO EQUIVIDA.DIRECCION("
				+ "SEC_PERSONA, DIRECCION, COD_TIPO_DIRECCION, SEC_PROVINCIA, SEC_CANTON, "
				+ "SEC_PARROQUIA, SEC_CANAL, ESTADO, USR_CREACION, TS_CREACION, " + "USR_MODIFICACION)VALUES" + "("
				+ direccion.getSecPersona().getSecPersona() + ", '" + direccion.getDireccion() + "', "
				+ direccion.getCodTipoDireccion().getCodTipoDireccion() + ", "
				+ direccion.getSecProvincia().getSecProvincia() + ", " + direccion.getSecCanton().getSecCanton() + ","
				+ "" + direccion.getSecParroquia().getSecParroquia() + ", " + direccion.getSecCanal().getSecCanal()
				+ ", '" + direccion.getEstado() + "', '" + direccion.getUsrCreacion() + "', '"
				+ direccion.getTsCreacion() + "'," + "'" + direccion.getUsrModificacion() + "')";

		Query insert = em.createNativeQuery(hql);
		insert.executeUpdate();
*/
		return true;
	}
}