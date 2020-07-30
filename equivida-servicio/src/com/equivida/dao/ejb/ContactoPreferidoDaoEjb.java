package com.equivida.dao.ejb;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.ContactoPreferidoDao;
import com.equivida.modelo.ContactoPreferido;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * @author Daniel Cardenas
 * 
 */
@Stateless(name = "ContactoPreferidoDao")
public class ContactoPreferidoDaoEjb extends
		GenericDaoEjb<ContactoPreferido, Long> implements ContactoPreferidoDao {

	public ContactoPreferidoDaoEjb() {
		super(ContactoPreferido.class);
	}

	@Override
	public void updateLight(ContactoPreferido contactoPreferido) {
		String hql = "update ContactoPreferido cp set cp.horarioInicio=:horarioInicio, cp.horarioFin=:horarioFin, "
				+ "cp.tipoContactoPreferido.codTipoContactoPreferido=:codTipoContactoPreferido,  "
				+ "cp.persona.secPersona=:secPersona ";
		if (contactoPreferido.getDireccion() != null
				&& contactoPreferido.getDireccion().getSecDireccion() != null) {
			hql += ", cp.direccion.secDireccion=:secDireccion ";
		}
		if (contactoPreferido.getTelefono() != null
				&& contactoPreferido.getTelefono().getSecTelefono() != null) {
			hql += ", cp.telefono.secTelefono=:secTelefono  ";
		}
		hql += "where cp.secContactoPreferido = :secContactoPreferido";

		Query query = em.createQuery(hql);

		query.setParameter("horarioInicio",
				contactoPreferido.getHorarioInicio());
		query.setParameter("horarioFin", contactoPreferido.getHorarioFin());
		query.setParameter("codTipoContactoPreferido", contactoPreferido
				.getTipoContactoPreferido().getCodTipoContactoPreferido());
		if (contactoPreferido.getTelefono() != null
				&& contactoPreferido.getTelefono().getSecTelefono() != null) {
			query.setParameter("secTelefono", contactoPreferido.getTelefono()
					.getSecTelefono());			
		}
		query.setParameter("secPersona", contactoPreferido.getPersona()
				.getSecPersona());
		if (contactoPreferido.getDireccion() != null
				&& contactoPreferido.getDireccion().getSecDireccion() != null) {
			query.setParameter("secDireccion", contactoPreferido.getDireccion()
					.getSecDireccion());
		}

		query.setParameter("secContactoPreferido",
				contactoPreferido.getSecContactoPreferido());

		query.executeUpdate();

	}
}
