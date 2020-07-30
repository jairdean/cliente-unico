package com.equivida.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.ActividadEconomicaDao;
import com.equivida.modelo.ActividadEconomica;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "ActividadEconomicaDao")
public class ActividadEconomicaDaoEjb extends GenericDaoEjb<ActividadEconomica, Short>
		implements ActividadEconomicaDao {

	public ActividadEconomicaDaoEjb() {
		super(ActividadEconomica.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String obtenerCiiuParaSise(Short codActividadEconomica) {

		StringBuilder sql = new StringBuilder();
		sql.append("select cod_ciiu from equivida.actividad_economica where COD_ACTIVIDAD_ECONOMICA=:cod ");

		Query q = em.createNativeQuery(sql.toString());

		q.setParameter("cod", codActividadEconomica);

		String codigoCiiu = "";

		List<Object> codigos = (List<Object>) q.getResultList();
		if (codigos.size() > 0) {
			codigoCiiu = codigos.get(0).toString();
		}

		return codigoCiiu;
	}

}
