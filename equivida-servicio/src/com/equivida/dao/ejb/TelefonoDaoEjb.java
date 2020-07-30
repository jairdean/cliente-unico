package com.equivida.dao.ejb;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.TelefonoDao;
import com.equivida.modelo.Telefono;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "TelefonoDao")
public class TelefonoDaoEjb extends GenericDaoEjb<Telefono, Integer> implements
		TelefonoDao {

	public TelefonoDaoEjb() {
		super(Telefono.class);
	}

	@Override
	public void updateLight(Telefono telefono) {

		String hql = "update Telefono t set t.codArea=:codArea, t.nroTelefono=:nroTelefono, "
				+ "t.extTelefono=:extTelefono, t.tipoTelefono.codTipoTelefono=:codTipoTelefono, t.pais.codPais=:codPais "
				+ "where t.secTelefono = :secTelefono";

		Query query = em.createQuery(hql);
		query.setParameter("codArea", telefono.getCodArea());
		query.setParameter("nroTelefono", telefono.getNroTelefono());
		query.setParameter("extTelefono", telefono.getExtTelefono());
		query.setParameter("codTipoTelefono", telefono.getTipoTelefono()
				.getCodTipoTelefono());
		query.setParameter("codPais", telefono.getPais().getCodPais());
		query.setParameter("secTelefono", telefono.getSecTelefono());

		query.executeUpdate();

	}
}
