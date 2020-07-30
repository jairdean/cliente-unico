package com.equivida.dao.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.EstadoCivilDao;
import com.equivida.modelo.EstadoCivil;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "EstadoCivilDao")
public class EstadoCivilDaoEjb extends GenericDaoEjb<EstadoCivil, Short>
		implements EstadoCivilDao {

	public EstadoCivilDaoEjb() {
		super(EstadoCivil.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.dao.EstadoCivilDao#getAllNativeQuery()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoCivil> getAllNativeQuery() {
		StringBuffer sql = new StringBuffer(50);
		sql.append("select e.COD_ESTADO_CIVIL, e.ESTADO_CIVIL from EQUIVIDA.ESTADO_CIVIL e order by e.COD_ESTADO_CIVIL");

		Query query = em.createNativeQuery(sql.toString());

		List<Object[]> respLis = query.getResultList();
		List<EstadoCivil> resp = new ArrayList<EstadoCivil>();

		for (Object[] obj : respLis) {
			resp.add(new EstadoCivil((Short) obj[0], (String) obj[1]));
		}

		return resp;
	}

}
