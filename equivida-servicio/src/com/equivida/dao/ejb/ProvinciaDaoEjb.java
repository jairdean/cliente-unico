package com.equivida.dao.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.ProvinciaDao;
import com.equivida.modelo.Provincia;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "ProvinciaDao")
public class ProvinciaDaoEjb extends GenericDaoEjb<Provincia, Short> implements ProvinciaDao {

	public ProvinciaDaoEjb() {
		super(Provincia.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.dao.ProvinciaDao#getByPaisNativeQuery(java.lang.Short)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Provincia> getByPaisNativeQuery(Short codigoPais) {
		StringBuffer sql = new StringBuffer(50);
		sql.append("select p.SEC_PROVINCIA, p.PROVINCIA from EQUIVIDA.PROVINCIA p ");
		sql.append("where p.COD_PAIS = ").append(codigoPais).append(" ");
		sql.append("order by p.PROVINCIA");

		Query query = em.createNativeQuery(sql.toString());

		List<Object[]> respLis = query.getResultList();
		List<Provincia> resp = new ArrayList<Provincia>();

		for (Object[] obj : respLis) {
			resp.add(new Provincia((Short) obj[0], (String) obj[1]));
		}

		return resp;
	}

}
