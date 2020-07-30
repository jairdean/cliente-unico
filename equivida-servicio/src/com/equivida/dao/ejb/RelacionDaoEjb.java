package com.equivida.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.constant.EstadoEnum;
import com.equivida.dao.RelacionDao;
import com.equivida.modelo.Relacion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "RelacionDao")
public class RelacionDaoEjb extends GenericDaoEjb<Relacion, Integer> implements RelacionDao {

	public RelacionDaoEjb() {
		super(Relacion.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Relacion> buscarPorTipoPersonaPrincipal(short idTipo, Integer secPersona, EstadoEnum estado) {
		StringBuilder ejbql = new StringBuilder();
		ejbql.append("select r FROM Relacion r where r.tipoParentescoRelacion.codTipoParentesco = :tipo ");
		ejbql.append(" AND r.persona1.secPersona=:secPersona ");
		if (estado != null) {
			ejbql.append(" AND r.estado='" + estado.getCodigo() + "'");
		}

		Query q = em.createQuery(ejbql.toString());
		q.setParameter("tipo", (short) idTipo);
		q.setParameter("secPersona", secPersona);

		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Relacion> buscarPorTipoPersonaPrincipal(short[] idTipo, Integer secPersona, EstadoEnum estado) {

		String tipoInLista = "";
		for (int i : idTipo) {
			tipoInLista += i + ",";
		}
		if (tipoInLista.endsWith(",")) {
			tipoInLista = tipoInLista.substring(0, tipoInLista.length() - 1);
		}

		StringBuilder ejbql = new StringBuilder();
		ejbql.append(
				"select r FROM Relacion r where r.tipoParentescoRelacion.codTipoParentesco in (" + tipoInLista + ")");
		ejbql.append(" AND r.persona1.secPersona=:secPersona ");
		if (estado != null) {
			ejbql.append(" AND r.estado='" + estado.getCodigo() + "'");
		}

		Query q = em.createQuery(ejbql.toString());
		q.setParameter("secPersona", secPersona);

		return q.getResultList();
	}
}