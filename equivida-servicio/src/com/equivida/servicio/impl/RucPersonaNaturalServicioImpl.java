package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.constant.Constantes;
import com.equivida.dao.RucPersonaNaturalDao;
import com.equivida.modelo.PersonaNatural;
import com.equivida.modelo.RucPersonaNatural;
import com.equivida.modelo.TipoIdentificacion;
import com.equivida.servicio.RucPersonaNaturalServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "RucPersonaNaturalServicio")
public class RucPersonaNaturalServicioImpl extends GenericServiceImpl<RucPersonaNatural, Integer>
		implements RucPersonaNaturalServicio {

	@EJB
	private RucPersonaNaturalDao rucPersonaNaturalDao;

	public GenericDao<RucPersonaNatural, Integer> getDao() {
		return rucPersonaNaturalDao;
	}

	@Override
	public List<RucPersonaNatural> buscarPoIdentificacion(String identificacion, Integer secPersonaNatural) {
		String[] criteriasAnd = { "identificacion", "personaNatural.secPersonaNatural" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.STRING_EQUALS, CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { identificacion, secPersonaNatural };
		Criteria criteria = new Criteria(criteriasAnd, typesAnd, valuesCriteriaAnd);
		return rucPersonaNaturalDao.findByCriterias(criteria);
	}

	@Override
	public void verificarRuc(PersonaNatural personaNatural) {
		if (personaNatural.getRucVerificarTransient() != null) {

			// si se envio RUC, entonces se veririca si guarda en ruc_persona_natural el ruc
			System.out.println("consulta el ruc si esta atado a la persona natural");
			if (personaNatural.getSecPersonaNatural() == null) {
				System.out.println("no realiza verificacion");
				return;
			}
			List<RucPersonaNatural> rucs = this.buscarPoIdentificacion(personaNatural.getRucVerificarTransient(),
					personaNatural.getSecPersonaNatural());
			if (rucs.isEmpty()) {
				// entonces no encontro ese ruc, lo guarda en cliente unico
				RucPersonaNatural nuevoRuc = new RucPersonaNatural();
				nuevoRuc.setIdentificacion(personaNatural.getRucVerificarTransient());
				nuevoRuc.setPersonaNatural(personaNatural);
				nuevoRuc.setTipoIdentificacion(new TipoIdentificacion(Constantes.TIPO_IDENTIFICACION_RUC));
				this.create(nuevoRuc);
			}
		}
	}
}