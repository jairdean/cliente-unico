package com.equivida.dao.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.ParroquiaDao;
import com.equivida.modelo.Parroquia;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "ParroquiaDao")
public class ParroquiaDaoEjb extends GenericDaoEjb<Parroquia, Short> implements
		ParroquiaDao {

	public ParroquiaDaoEjb() {
		super(Parroquia.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.dao.ParroquiaDao#getByCantonNativeQuery(java.lang.Short)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Parroquia> getByCantonNativeQuery(Short codigoCanton) {
		StringBuffer sql = new StringBuffer(50);
		sql.append("select p.SEC_PARROQUIA, p.PARROQUIA from EQUIVIDA.PARROQUIA p ");
		sql.append("where p.SEC_CANTON = ").append(codigoCanton).append(" ");
		sql.append("order by p.PARROQUIA");

		Query query = em.createNativeQuery(sql.toString());

		List<Object[]> respLis = query.getResultList();
		List<Parroquia> resp = new ArrayList<Parroquia>();

		for (Object[] obj : respLis) {
			resp.add(new Parroquia((Short) obj[0], (String) obj[1]));
		}

		return resp;
	}

}
