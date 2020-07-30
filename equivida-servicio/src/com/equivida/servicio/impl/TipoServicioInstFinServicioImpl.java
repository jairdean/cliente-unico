package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.constant.RespuestaEnum;
import com.equivida.dao.TipoServicioInstFinDao;
import com.equivida.modelo.TipoServicioInstFin;
import com.equivida.servicio.TipoServicioInstFinServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "TipoServicioInstFinServicio")
public class TipoServicioInstFinServicioImpl extends
		GenericServiceImpl<TipoServicioInstFin, Short> implements
		TipoServicioInstFinServicio {

	@EJB
	private TipoServicioInstFinDao tipoServicioInstFinDao;

	public GenericDao<TipoServicioInstFin, Short> getDao() {
		return tipoServicioInstFinDao;
	}

	@Override
	public List<TipoServicioInstFin> obtenerPorTipoFinanciera(
			Short secTipoFinanciera) {

		String[] criteriasAnd = {
				"tipoInstitucionFinanciera.secTipoFinanciera", "codigoVisible" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.SHORT_EQUALS,
				CriteriaTypeEnum.STRING_EQUALS };
		Object[] valuesCriteriaAnd = { secTipoFinanciera,
				RespuestaEnum.SI.getCodigo() };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<TipoServicioInstFin> lista = tipoServicioInstFinDao
				.findByCriterias(criteria);

		return lista;

	}
}