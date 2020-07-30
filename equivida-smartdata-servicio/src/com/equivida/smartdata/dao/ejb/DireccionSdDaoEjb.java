/**
 *DireccionDaoEjb.java
 *
 *Tue Jan 26 12:34:07 ECT 2016
 */
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.constante.EstadoEnum;
import com.equivida.smartdata.dao.DireccionSdDao;
import com.equivida.smartdata.model.DireccionSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "DireccionSdDao")
public class DireccionSdDaoEjb extends GenericDaoEjb<DireccionSd, Integer>
		implements DireccionSdDao {

	public DireccionSdDaoEjb() {
		super(DireccionSd.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.dao.DireccionSdDao#desactivarDireccionesPersona
	 * (java.lang.Integer)
	 */
	@Override
	public void desactivarDireccionesPersona(Integer secPersona) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("update DireccionSd d set d.estado = :estado where d.secPersona.secPersona = :secPersona");

		Query update = em.createQuery(sql.toString());
		update.setParameter("estado", EstadoEnum.I.getEstadoChar());
		update.setParameter("secPersona", secPersona);

		update.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.dao.DireccionSdDao#obtenerPorDocumentoDireccion
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public DireccionSd obtenerPorDocumentoDireccion(String noDocumento,
			String direccion) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("select d from DireccionSd d where ");
		sql.append("d.secPersona.identificacion = :identificacion ");
		sql.append("and d.direccion = :direccion");

		Query query = em.createQuery(sql.toString());
		query.setParameter("identificacion", noDocumento);
		query.setParameter("direccion", direccion);

		List<DireccionSd> direccionList = query.getResultList();

		if (direccionList != null && !direccionList.isEmpty()) {
			return direccionList.get(0);
		}

		return null;
	}
}