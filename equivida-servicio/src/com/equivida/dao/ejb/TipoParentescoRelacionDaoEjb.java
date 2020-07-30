package com.equivida.dao.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.TipoParentescoRelacionDao;
import com.equivida.modelo.TipoParentescoRelacion;
import com.equivida.util.StringUtil;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "TipoParentescoRelacionDao")
public class TipoParentescoRelacionDaoEjb extends GenericDaoEjb<TipoParentescoRelacion, Short>
		implements TipoParentescoRelacionDao {

	public TipoParentescoRelacionDaoEjb() {
		super(TipoParentescoRelacion.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoParentescoRelacion> obtenerPorIds(short[] ids) {

		if (ids == null) {
			return new ArrayList<TipoParentescoRelacion>();
		}

		String inList = StringUtil.armarInList(ids);

		String ejbql = "select t from TipoParentescoRelacion t where t.codTipoParentesco in (" + inList + ")";

		Query q = em.createQuery(ejbql);

		return q.getResultList();
	}
}
