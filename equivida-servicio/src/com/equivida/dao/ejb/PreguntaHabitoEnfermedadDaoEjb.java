package com.equivida.dao.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.PreguntaHabitoEnfermedadDao;
import com.equivida.modelo.PreguntaHabitoEnfermedad;
import com.equivida.util.StringUtil;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "PreguntaHabitoEnfermedadDao")
public class PreguntaHabitoEnfermedadDaoEjb extends GenericDaoEjb<PreguntaHabitoEnfermedad, Integer>
		implements PreguntaHabitoEnfermedadDao {

	public PreguntaHabitoEnfermedadDaoEjb() {
		super(PreguntaHabitoEnfermedad.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<PreguntaHabitoEnfermedad> buscarPorCategoria(short codTipoHabito, String[] categorias) {
		String inList = StringUtil.armarInList(categorias);

		StringBuilder sb = new StringBuilder();
		sb.append(
				"select p from PreguntaHabitoEnfermedad p where p.tipoHabitoEnfermedad.codTipoHabito =:codTipoHabito");
		sb.append(" and p.categoria in (");
		sb.append(inList);
		sb.append(" ) ");

		Query q = em.createQuery(sb.toString());
		q.setParameter("codTipoHabito", codTipoHabito);

		return q.getResultList();
	}

}
