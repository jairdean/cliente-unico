package com.equivida.dao.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.TipoHabitoEnfermedadDao;
import com.equivida.modelo.TipoHabitoEnfermedad;
import com.equivida.util.StringUtil;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * Clase que implementa TipoHabitoEnfermedadDao
 * 
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "TipoHabitoEnfermedadDao")
public class TipoHabitoEnfermedadDaoEjb extends GenericDaoEjb<TipoHabitoEnfermedad, Short>
		implements TipoHabitoEnfermedadDao {

	public TipoHabitoEnfermedadDaoEjb() {
		super(TipoHabitoEnfermedad.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TipoHabitoEnfermedad> buscarPorCategoria(String[] categorias) {

		String inList = StringUtil.armarInList(categorias);

		StringBuilder sb = new StringBuilder();
		sb.append("select t from TipoHabitoEnfermedad t where t.categoria in (");
		sb.append(inList);
		sb.append(" ) ");

		Query q = em.createQuery(sb.toString());

		return q.getResultList();
	}
}
