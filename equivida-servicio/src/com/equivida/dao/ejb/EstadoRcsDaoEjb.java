package com.equivida.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.EstadoRcsDao;
import com.equivida.modelo.EstadoRcs;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "EstadoRcsDao")
public class EstadoRcsDaoEjb extends GenericDaoEjb<EstadoRcs, Character>
		implements EstadoRcsDao {

	public EstadoRcsDaoEjb() {
		super(EstadoRcs.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoRcs> obtenerTodos() {
		
		String hql="select rcs from EstadoRcs rcs order by codEstado";
		
		Query query = em.createQuery(hql);

		return query.getResultList();
	}
}
