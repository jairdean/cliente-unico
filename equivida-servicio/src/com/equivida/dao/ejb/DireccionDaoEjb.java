package com.equivida.dao.ejb;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.dao.DireccionDao;
import com.equivida.modelo.Direccion;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

/**
 * @author Gerardo Tuquerrez
 * 
 */
@Stateless(name = "DireccionDao")
public class DireccionDaoEjb extends GenericDaoEjb<Direccion, Integer>
		implements DireccionDao {

	public DireccionDaoEjb() {
		super(Direccion.class);
	}

	@Override
	public void updateLight(Direccion direccion) {
		String hql = "update Direccion d set d.principal=:principal, d.numero=:numero, "
				+ "d.secundaria=:secundaria, d.barrio=:barrio, d.edificio=:edificio, "
				+ "d.piso=:piso, d.oficina=:oficina, d.envioCorrespondencia=:envioCorrespondencia, "
				+ "d.verificada=:verificada, d.referencia=:referencia, d.latitud=:latitud, "
				+ "d.longitud=:longitud, d.tipoDireccion.codTipoDireccion=:codTipoDireccion, d.canton.secCanton=:secCanton, "
				+ "d.ciudad=:ciudad, d.estado=:estado "
				+ "where d.secDireccion = :secDireccion";

		Query query = em.createQuery(hql);
		query.setParameter("principal", direccion.getPrincipal());
		query.setParameter("numero", direccion.getNumero());
		query.setParameter("secundaria", direccion.getSecundaria());
		query.setParameter("barrio", direccion.getBarrio());
		query.setParameter("edificio", direccion.getEdificio());
		query.setParameter("piso", direccion.getPiso());
		query.setParameter("oficina", direccion.getOficina());
		query.setParameter("envioCorrespondencia",
				direccion.getEnvioCorrespondencia());
		query.setParameter("verificada", direccion.getVerificada());
		query.setParameter("referencia", direccion.getReferencia());
		query.setParameter("latitud", direccion.getLatitud());
		query.setParameter("longitud", direccion.getLongitud());
		query.setParameter("codTipoDireccion", direccion.getTipoDireccion()
				.getCodTipoDireccion());
		query.setParameter("secCanton", direccion.getCanton().getSecCanton());
		query.setParameter("ciudad", direccion.getCiudad());
		query.setParameter("estado", direccion.getEstado());

		query.setParameter("secDireccion", direccion.getSecDireccion());
		query.executeUpdate();
	}
}
