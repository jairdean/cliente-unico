package com.equivida.dao.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.PaisDao;
import com.equivida.modelo.Pais;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "PaisDao")
public class PaisDaoEjb extends GenericDaoEjb<Pais, Short> implements PaisDao {

	public PaisDaoEjb() {
		super(Pais.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.dao.PaisDao#getAllNativeQuery()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Pais> getAllNativeQuery() {
		StringBuffer sql = new StringBuffer(50);
		sql.append("select p.COD_PAIS, p.PAIS from EQUIVIDA.PAIS p order by p.COD_PAIS");

		Query query = em.createNativeQuery(sql.toString());

		List<Object[]> respLis = query.getResultList();
		List<Pais> resp = new ArrayList<Pais>();

		for (Object[] obj : respLis) {
			resp.add(new Pais((Short) obj[0], (String) obj[1]));
		}

		return resp;
	}

}