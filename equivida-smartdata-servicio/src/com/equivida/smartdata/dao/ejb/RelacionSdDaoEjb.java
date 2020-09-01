/**
*RelacionDaoEjb.java
*
*Thu Feb 18 10:46:12 ECT 2016
*/
package com.equivida.smartdata.dao.ejb;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.constante.TipoParentescoEnum;
import com.equivida.smartdata.constante.UsuarioEnum;
import com.equivida.smartdata.dao.RelacionSdDao;
import com.equivida.smartdata.model.RelacionSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "RelacionSdDao")
public class RelacionSdDaoEjb extends GenericDaoEjb<RelacionSd, Integer> implements RelacionSdDao {

	public RelacionSdDaoEjb() {
		super(RelacionSd.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.DireccionSdDao#
	 * CrearRelacion
	 */
	@Override
	public void CrearRelacion(Integer secPersonaP, Integer secPersonaR) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"insert into EQUIVIDA.RELACION ( SEC_PERSONA_P, SEC_PERSONA_R, COD_TIPO_PARENTESCO, SEC_CANAL, USR_CREACION, TS_CREACION,  USR_MODIFICACION)");
		sql.append("values (:secPersonaP, :secPersonaR, :tipoParentesco, :secCanal, :usrCreacion, :tsCreacion, :usrModificacion) ");

		Query q = em.createNativeQuery(sql.toString());

		q.setParameter("secPersonaP", secPersonaP);
		q.setParameter("secPersonaR", secPersonaR);
		q.setParameter("tipoParentesco", TipoParentescoEnum.CONYUGE.getCodigoTipoParentesco());
		q.setParameter("secCanal", "1");
		q.setParameter("usrCreacion", UsuarioEnum.USUARIO_CREACION.getValor());
		q.setParameter("tsCreacion", new Date());
		q.setParameter("usrModificacion", UsuarioEnum.USUARIO_MODIFICACION.getValor());
		
		q.executeUpdate();
		
	}
}