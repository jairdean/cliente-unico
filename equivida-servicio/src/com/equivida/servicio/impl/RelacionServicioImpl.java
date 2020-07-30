package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.constant.Constantes;
import com.equivida.constant.EstadoEnum;
import com.equivida.dao.RelacionDao;
import com.equivida.modelo.Relacion;
import com.equivida.servicio.RelacionServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "RelacionServicio")
public class RelacionServicioImpl extends GenericServiceImpl<Relacion, Integer> implements RelacionServicio {

	@EJB
	private RelacionDao relacionDao;

	public GenericDao<Relacion, Integer> getDao() {
		return relacionDao;
	}

	@Override
	public List<Relacion> obtener(Integer secPersonaPrincipal, Integer secPersonaRelacion) {

		String[] criteriasAnd = { "persona1.secPersona", "persona.secPersona",
				"tipoParentescoRelacion.codTipoParentesco" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS,
				CriteriaTypeEnum.SHORT_EQUALS };
		Object[] valuesCriteriaAnd = { secPersonaPrincipal, secPersonaRelacion, Constantes.TIPO_RELACION_CONYUGE };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);

		return this.findByCriterias(criteria);

	}

	@Override
	public List<Relacion> buscarPorTipoPersonaPrincipal(short idTipo, Integer secPersona, EstadoEnum estado) {
		return relacionDao.buscarPorTipoPersonaPrincipal(idTipo, secPersona, estado);
	}

	@Override
	public List<Relacion> buscarPorTipoPersonaPrincipal(short[] idTipo, Integer secPersona, EstadoEnum estado) {
		return relacionDao.buscarPorTipoPersonaPrincipal(idTipo, secPersona, estado);
	}
}
