package com.equivida.dao.ejb;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.DireccionTelefonoDao;
import com.equivida.modelo.DireccionTelefono;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "DireccionTelefonoDao")
public class DireccionTelefonoDaoEjb extends
		GenericDaoEjb<DireccionTelefono, Integer> implements
		DireccionTelefonoDao {

	public DireccionTelefonoDaoEjb() {
		super(DireccionTelefono.class);
	}

	@Override
	public void updateLigth(DireccionTelefono direccionTelefono) {

		String hql = "update DireccionTelefono dt set dt.telefono.secTelefono=:secTelefono, dt.persona.secPersona=:secPersona, dt.direccion.secDireccion=:secDireccion "
				+ "where dt.secDireccionTelefono = :secDireccionTelefono";

		Query query = em.createQuery(hql);

		query.setParameter("secTelefono", direccionTelefono.getTelefono()
				.getSecTelefono());
		query.setParameter("secPersona", direccionTelefono.getPersona()
				.getSecPersona());
		query.setParameter("secDireccion", direccionTelefono.getDireccion()
				.getSecDireccion());

		query.setParameter("secDireccionTelefono",
				direccionTelefono.getSecDireccionTelefono());

		query.executeUpdate();

	}
}
