package com.equivida.dao.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.TipoIdentificacionDao;
import com.equivida.modelo.TipoIdentificacion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "TipoIdentificacionDao")
public class TipoIdentificacionDaoEjb extends GenericDaoEjb<TipoIdentificacion, Character>
		implements TipoIdentificacionDao {

	public TipoIdentificacionDaoEjb() {
		super(TipoIdentificacion.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.dao.TipoIdentificacionDao#getAllNativeQuery()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoIdentificacion> getAllNativeQuery() {
		StringBuffer sql = new StringBuffer(50);
		sql.append(
				"select t.COD_TIPO_IDENTIFICACION, t.TIPO_IDENTIFICACION from EQUIVIDA.TIPO_IDENTIFICACION t where t.CODIGO_VISIBLE = 'S' order by t.TIPO_IDENTIFICACION");

		Query query = em.createNativeQuery(sql.toString());

		List<Object[]> respLis = query.getResultList();
		List<TipoIdentificacion> resp = new ArrayList<TipoIdentificacion>();

		for (Object[] obj : respLis) {
			resp.add(new TipoIdentificacion((Character) obj[0], (String) obj[1]));
		}

		return resp;
	}

}
