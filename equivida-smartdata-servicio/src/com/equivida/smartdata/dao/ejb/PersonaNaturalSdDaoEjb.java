/**
 *PersonaNaturalDaoEjb.java
 *
 *Tue Jan 26 12:42:07 ECT 2016
 */
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.equivida.smartdata.dao.PersonaNaturalSdDao;
import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.model.PersonaNaturalSd;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "PersonaNaturalSdDao")
public class PersonaNaturalSdDaoEjb extends GenericDaoEjb<PersonaNaturalSd, Integer> implements PersonaNaturalSdDao {

	public PersonaNaturalSdDaoEjb() {
		super(PersonaNaturalSd.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.PersonaNaturalSdDao#actualizaDatosPersonales
	 * (com.equivida.smartdata.dto.DatosActualizaSdDto)
	 */
	@Override
	public void actualizaDatosPersonales(DatosActualizaSdDto datosActualiza) {
		StringBuffer sql = new StringBuffer(300);

		sql.append("update PersonaNaturalSd p set ");
		sql.append("p.apellidoPaterno = :apellidoPaterno, ");
		sql.append("p.apellidoMaterno = :apellidoMaterno, ");
		sql.append("p.primerNombre = :primerNombre, ");
		sql.append("p.segundoNombre = :segundoNombre, ");
		sql.append("p.sexo = :sexo, ");
		sql.append("p.codEstadoCivil.codEstadoCivil = :codEstadoCivil, ");
		sql.append("p.fchNacimiento = :fchNacimiento, ");
		sql.append("p.fchFallecimiento = :fchFallecimiento, ");
		sql.append("p.usrModificacion = :usrModificacion, ");
		sql.append("p.secCanal.secCanal = :secCanal ");
		sql.append("where p.secPersona.secPersona = :secPersona");

		Query update = em.createQuery(sql.toString());

		update.setParameter("apellidoPaterno", datosActualiza.getApellidoPaterno());
		update.setParameter("apellidoMaterno", datosActualiza.getApellidoMaterno());
		update.setParameter("primerNombre", datosActualiza.getPrimerNombre());
		update.setParameter("segundoNombre", datosActualiza.getSegundoNombre());
		update.setParameter("sexo", datosActualiza.getSexo());
		update.setParameter("codEstadoCivil", datosActualiza.getCodEstadoCivil());
		update.setParameter("fchNacimiento", datosActualiza.getFchNacimiento());
		update.setParameter("fchFallecimiento", datosActualiza.getFchFallecimiento());
		update.setParameter("usrModificacion", datosActualiza.getUsrProcesa());
		update.setParameter("secCanal", datosActualiza.getSecCanal());
		update.setParameter("secPersona", datosActualiza.getSecPersonaActualiza());

		update.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.dao.PersonaNaturalSdDao#obtenerPersonaByIdentificacion
	 * (java.lang.String)
	 */
	@Override
	public PersonaNaturalSd obtenerPersonaByIdentificacion(String noDocumento) {
		Query query = em.createNamedQuery("PersonaNaturalSd.findByIdentificacion");
		query.setParameter("identificacion", noDocumento);

		System.out.println("=======================CONSULTA SD 1");
		List<PersonaNaturalSd> personaList = query.getResultList();
		System.out.println("=======================CONSULTA SD 2");

		if (personaList != null && !personaList.isEmpty()) {
			return personaList.get(0);
		}

		return null;
	}
}