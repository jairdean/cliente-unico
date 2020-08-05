/**
 *PersonaDaoEjb.java
 *
 *Tue Jan 26 12:39:40 ECT 2016
 */
package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.equivida.smartdata.dao.PersonaSdDao;
import com.equivida.smartdata.dto.DatosActualizaSdDto;
import com.equivida.smartdata.model.PersonaSd;
import com.equivida.smartdata.servicio.impl.SmartDataSdServicioImpl;
import com.saviasoft.persistence.util.dao.ejb.GenericDaoEjb;

@Stateless(name = "PersonaSdDao")
public class PersonaSdDaoEjb extends GenericDaoEjb<PersonaSd, Integer> implements PersonaSdDao {

	private Logger log = Logger.getLogger(SmartDataSdServicioImpl.class);

	public PersonaSdDaoEjb() {
		super(PersonaSd.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.PersonaSdDao#actualizaDatosPersonales(com.
	 * equivida.smartdata.dto.DatosActualizaSdDto)
	 */
	@Override
	public void actualizaDatosPersonales(DatosActualizaSdDto datosActualiza) {
		StringBuffer sql = new StringBuffer(300);

		sql.append("update EQUIVIDA.PERSONA set ");
		sql.append("DENOMINACION = '").append(datosActualiza.getNombresApellidos()).append("' ");
		sql.append("where SEC_PERSONA = ").append(datosActualiza.getSecPersonaActualiza());

		System.out.println("========================================");
		System.out.println(sql.toString());
		System.out.println("========================================");

		Query update = em.createNativeQuery(sql.toString());

		update.executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.PersonaSdDao#obtenerPersonaByIdentificacion
	 * (java.lang.String)
	 */
	@Override
	public PersonaSd obtenerPersonaByIdentificacion(String noDocumento) {
		String hql = "select p from PersonaSd p where p.identificacion = '".concat(noDocumento).concat("'");

		Query query = em.createQuery(hql);

		log.error(hql);

		List<PersonaSd> respList = query.getResultList();

		if (respList != null && !respList.isEmpty()) {
			return respList.get(0);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.equivida.smartdata.dao.PersonaSdDao#ingresaPersona
	 * (java.lang.String)
	 */
	@Override
	public boolean ingresaPersona(PersonaSd persona) {

		String hql = "INSERT INTO EQUIVIDA.PERSONA(COD_TIPO_IDENTIFICACION, IDENTIFICACION, DENOMINACION) VALUES ('"
				+ persona.getCodTipoIdentificacion().getCodTipoIdentificacion() + "','" + persona.getIdentificacion()
				+ "','" + persona.getDenominacion() + "')";

		log.error("INSERTA PERSONA");
		log.error(hql);
		/*Query insert = em.createNativeQuery(hql);
		insert.executeUpdate();*/

		return true;
	}

}