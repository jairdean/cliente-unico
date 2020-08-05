/**
 *TelefonoDaoEjb.java
 *
 *Tue Jan 26 12:46:16 ECT 2016
 */
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.constante.EstadoEnum;
import com.equivida.smartdata.dao.TelefonoSdDao;
import com.equivida.smartdata.model.DireccionSd;
import com.equivida.smartdata.model.TelefonoSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "TelefonoSdDao")
public class TelefonoSdDaoEjb extends GenericDaoEjb<TelefonoSd, Integer> implements TelefonoSdDao {

	public TelefonoSdDaoEjb() {
		super(TelefonoSd.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.TelefonoSdDao#desactivarTelefonsosPersona(
	 * java.lang.Integer)
	 */
	@Override
	public void desactivarTelefonsosPersona(Integer secPersona) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("update TelefonoSd t set t.estado = :estado where t.secPersona.secPersona = :secPersona");

		Query update = em.createQuery(sql.toString());
		update.setParameter("estado", EstadoEnum.I.getEstadoChar());
		update.setParameter("secPersona", secPersona);

		update.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.TelefonoSdDao#obtenerPorDocumentoTelefono(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public TelefonoSd obtenerPorDocumentoTelefono(String noDocumento, String telefono) {
		StringBuffer hql = new StringBuffer(200);
		hql.append("select t from TelefonoSd t where ");
		hql.append("t.secPersona.identificacion =:identificacion ");
		hql.append("and t.nroTelefono = :nroTelefono");

		Query query = em.createQuery(hql.toString());
		query.setParameter("identificacion", noDocumento);
		query.setParameter("nroTelefono", telefono);

		List<TelefonoSd> respList = query.getResultList();

		if (respList != null && !respList.isEmpty()) {
			return respList.get(0);
		}

		return null;
	}
}