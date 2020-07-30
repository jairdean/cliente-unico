package com.equivida.homologacion.dao.ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.jboss.ejb3.annotation.TransactionTimeout;

import com.equivida.homologacion.constant.SistemaEnum;
import com.equivida.homologacion.dao.PersonaEquividaDao;
import com.equivida.homologacion.modelo.PersonaEquivida;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "PersonaEquividaDao")
public class PersonaEquividaDaoEjb extends
		GenericDaoEjb<PersonaEquivida, Integer> implements PersonaEquividaDao {

	public PersonaEquividaDaoEjb() {
		super(PersonaEquivida.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, Long> obtenerSecuencialPersona(List<Long> idssSise) {

		String ejbql = "select new map(p.idPersonaDestino as idSise, p.secPersonaEquivida as idCU) from PersonaEquivida p where p.codSistema = :codSistema and p.idPersonaDestino in :idPersonaDestino ";
		Query query = em.createQuery(ejbql);
		query.setParameter("codSistema", SistemaEnum.SISE.getCodigo());
		query.setParameter("idPersonaDestino", idssSise);

		List<Map<String, Object>> ret = query.getResultList();

		Map<Long, Long> resp = new HashMap<Long, Long>();
		for (Map<String, Object> m : ret) {
			Integer it = (Integer) m.get("idCU");

			resp.put((Long) m.get("idSise"), it.longValue());
		}

		return resp;

	}
}