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

	@Override
	public boolean ingresarDireccionElectronica(DireccionElectronicaSd direccion) {
		String hql = "INSERT INTO EQUIVIDA.DIRECCION_ELECTRONICA("
				+ "SEC_PERSONA, DIRECCION_ELECTRONICA, COD_TIPO_DIRECCION_ELECTRONICA, SEC_CANAL, ESTADO,"
				+ "USR_CREACION, TS_CREACION, USR_MODIFICACION" + ")VALUES" + "(" + direccion.getSecPersona() + ", '"
				+ direccion.getDireccionElectronica() + "', " + direccion.getCodTipoDireccionElectronica() + ", "
				+ direccion.getSecCanal() + ", '" + direccion.getEstado() + "'," + "'" + direccion.getUsrCreacion()
				+ "', '" + direccion.getTsCreacion() + "', '" + direccion.getTsCreacion() + "' )";

		Query insert = em.createNativeQuery(hql);
		insert.executeUpdate();

		return true;
	}

}