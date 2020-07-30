package com.equivida.dao.ejb;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.HabitoEnfermedadDao;
import com.equivida.modelo.HabitoEnfermedad;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "HabitoEnfermedadDao")
public class HabitoEnfermedadDaoEjb extends
		GenericDaoEjb<HabitoEnfermedad, Integer> implements HabitoEnfermedadDao {

	public HabitoEnfermedadDaoEjb() {
		super(HabitoEnfermedad.class);
	}

	@Override
	public void updateLight(HabitoEnfermedad habitoEnfermedad) {
		String hql = "update HabitoEnfermedad he set he.tipoHabitoEnfermedad.codTipoHabito=:codTipoHabito, "
				+ "he.personaNatural.secPersonaNatural=:secPersonaNatural "
				+ "where he.secHabitoEnfermedad = :secHabitoEnfermedad and he.respuesta=:respuesta";

		Query query = em.createQuery(hql);

		System.out.println(habitoEnfermedad.getTipoHabitoEnfermedad()
				.getCodTipoHabito());

		System.out.println(habitoEnfermedad.getPersonaNatural()
				.getSecPersonaNatural());

		System.out.println(habitoEnfermedad.getSecHabitoEnfermedad());

		System.out.println(habitoEnfermedad.getRespuesta());

		query.setParameter("codTipoHabito", habitoEnfermedad
				.getTipoHabitoEnfermedad().getCodTipoHabito());
		query.setParameter("secPersonaNatural", habitoEnfermedad
				.getPersonaNatural().getSecPersonaNatural());
		query.setParameter("secHabitoEnfermedad",
				habitoEnfermedad.getSecHabitoEnfermedad());
		query.setParameter("respuesta", habitoEnfermedad.getRespuesta());

		query.executeUpdate();

	}

}