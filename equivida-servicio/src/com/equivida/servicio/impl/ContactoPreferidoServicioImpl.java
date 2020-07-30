package com.equivida.servicio.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.equivida.dao.ContactoPreferidoDao;
import com.equivida.modelo.ContactoPreferido;
import com.equivida.servicio.ContactoPreferidoServicio;
import com.saviasoft.persistence.util.constant.CriteriaTypeEnum;
import com.saviasoft.persistence.util.dao.GenericDao;
import com.saviasoft.persistence.util.service.impl.GenericServiceImpl;
import com.saviasoft.util.Criteria;

/**
 * @author Daniel Cardenas
 */
@Stateless(name = "ContactoPreferidoServicio")
public class ContactoPreferidoServicioImpl extends
		GenericServiceImpl<ContactoPreferido, Long> implements
		ContactoPreferidoServicio {

	@EJB
	private ContactoPreferidoDao contactoPreferidoDao;

	public GenericDao<ContactoPreferido, Long> getDao() {
		return contactoPreferidoDao;
	}

	@Override
	public ContactoPreferido obtenerPorPersona(Integer secPersona) {

		String[] criteriasAnd = { "persona.secPersona" };
		CriteriaTypeEnum[] typesAnd = { CriteriaTypeEnum.INTEGER_EQUALS };
		Object[] valuesCriteriaAnd = { secPersona };

		Criteria criteria = new Criteria(criteriasAnd, typesAnd,
				valuesCriteriaAnd);

		List<ContactoPreferido> lista = contactoPreferidoDao
				.findByCriterias(criteria);

		ContactoPreferido contactoPreferido = null;

		if (lista.size() >= 1) {
			contactoPreferido = lista.get(0);
			if (lista.size() > 1) {
				System.out
						.println("WARN: existe mas un contacto preferido atada a una persona ");
			}
		}

		return contactoPreferido;

	}

	@Override
	public void updateLight(ContactoPreferido contactoPreferido) {
		contactoPreferidoDao.updateLight(contactoPreferido);
	}
}