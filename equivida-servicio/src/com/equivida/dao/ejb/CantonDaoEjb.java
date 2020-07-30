package com.equivida.dao.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.CantonDao;
import com.equivida.modelo.Canton;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "CantonDao")
public class CantonDaoEjb extends GenericDaoEjb<Canton, Short> implements
		CantonDao {

	public CantonDaoEjb() {
		super(Canton.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.dao.CantonDao#getByProvinciaNativeQuery(java.lang.Short)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Canton> getByProvinciaNativeQuery(Short codigoProvincia) {
		StringBuffer sql = new StringBuffer(50);
		sql.append("select c.SEC_CANTON, c.CANTON from EQUIVIDA.CANTON c ");
		sql.append("where c.SEC_PROVINCIA = ").append(codigoProvincia)
				.append(" ");
		sql.append("order by c.CANTON");

		Query query = em.createNativeQuery(sql.toString());

		List<Object[]> respLis = query.getResultList();
		List<Canton> resp = new ArrayList<Canton>();

		for (Object[] obj : respLis) {
			resp.add(new Canton((Short) obj[0], (String) obj[1]));
		}

		return resp;
	}

}
