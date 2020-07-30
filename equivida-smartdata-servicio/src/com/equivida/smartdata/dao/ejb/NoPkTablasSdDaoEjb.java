package com.equivida.smartdata.dao.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.equivida.smartdata.constante.ConsultaEnEnum;
import com.equivida.smartdata.dao.NoPkTablasSdDao;

/**
 * 
 * @author juan
 *
 */
@Stateless(name = "NoPkTablasSdDao")
public class NoPkTablasSdDaoEjb implements NoPkTablasSdDao {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.equivida.smartdata.dao.NoPkTablasDao#obtenerCodigoPaisPorCodigoDB(
	 * java.lang.Short)
	 */
	@Override
	public Short obtenerCodigoPaisPorCodigoDB(Short codigoPaisDB) {
		StringBuffer sql = new StringBuffer(100);
		sql.append("SELECT COD_PAIS FROM EQUIVIDA.PAIS_OTROS WHERE COD_PAIS_RC = ").append(codigoPaisDB);

		Query query = em.createNativeQuery(sql.toString());

		List<Short> listaTmp = query.getResultList();

		Short resp = null;

		if (listaTmp != null && !listaTmp.isEmpty()) {
			resp = listaTmp.get(0);
		}

		return resp;
	}

	@Override
	public Short obtenerCodigoProvinciaPorCodigoDB(String codigoProvinciaDB, ConsultaEnEnum consultarEn) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("SELECT SEC_PROVINCIA FROM EQUIVIDA.PROVINCIA_OTROS WHERE ");

		if (ConsultaEnEnum.IESS.equals(consultarEn)) {
			sql.append("COD_PROVINCIA_IESS = '").append(codigoProvinciaDB).append("' ");
		} else {
			sql.append("COD_PROVINCIA_SRI = '").append(codigoProvinciaDB).append("' ");
		}

		Query query = em.createNativeQuery(sql.toString());

		List<Short> listaTmp = query.getResultList();

		Short resp = null;

		if (listaTmp != null && !listaTmp.isEmpty()) {
			resp = listaTmp.get(0);
		}

		return resp;
	}

	@Override
	public Short obtenerCodigoCantonPorCodigoDB(String codigoCantonDB, ConsultaEnEnum consultarEn) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("SELECT SEC_CANTON FROM EQUIVIDA.CANTON_OTROS WHERE ");

		if (ConsultaEnEnum.IESS.equals(consultarEn)) {
			sql.append("COD_CANTON_IESS = '").append(codigoCantonDB).append("' ");
		} else {
			sql.append("COD_CANTON_SRI = '").append(codigoCantonDB).append("' ");
		}

		Query query = em.createNativeQuery(sql.toString());

		List<Short> listaTmp = query.getResultList();

		Short resp = null;

		if (listaTmp != null && !listaTmp.isEmpty()) {
			resp = listaTmp.get(0);
		}

		return resp;
	}

	@Override
	public Short obtenerCodigoParroquiaPorCodigoDB(String codigoParroquiaDB, ConsultaEnEnum consultarEn) {
		StringBuffer sql = new StringBuffer(200);
		sql.append("SELECT SEC_PARROQUIA FROM EQUIVIDA.PARROQUIA_OTROS WHERE ");

		if (ConsultaEnEnum.IESS.equals(consultarEn)) {
			sql.append("COD_PARROQUIA_IESS = '").append(codigoParroquiaDB).append("' ");
		} else {
			sql.append("COD_PARROQUIA_SRI = '").append(codigoParroquiaDB).append("' ");
		}

		Query query = em.createNativeQuery(sql.toString());

		List<Short> listaTmp = query.getResultList();

		Short resp = null;

		if (listaTmp != null && !listaTmp.isEmpty()) {
			resp = listaTmp.get(0);
		}

		return resp;
	}

}
